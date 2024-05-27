package icu.dbkx.opnmb.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import icu.dbkx.opnmb.common.pojo.dto.LoginDto;
import icu.dbkx.opnmb.common.pojo.dto.RegisterDto;
import icu.dbkx.opnmb.common.pojo.vo.resp.LoginResp;
import icu.dbkx.opnmb.common.utils.AssertUtil;
import icu.dbkx.opnmb.common.utils.JwtUtil;
import icu.dbkx.opnmb.event.UserLoginEvent;
import icu.dbkx.opnmb.generator.entity.Captcha;
import icu.dbkx.opnmb.generator.entity.User;
import icu.dbkx.opnmb.generator.service.CaptchaService;
import icu.dbkx.opnmb.common.pojo.Result;
import icu.dbkx.opnmb.generator.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CaptchaService captchaService;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    /**
     * 发送验证码
     */
    @PostMapping("/send_captcha")
    public Result<String> sendCaptcha(@RequestParam String email) {
        Captcha captcha = new Captcha();
        int code = new Random().nextInt(900000) + 100000;
        captcha.setEmail(email);
        captcha.setCaptcha(Integer.toString(code));
        captchaService.saveOrUpdate(captcha);
        captchaService.sendCaptcha(email, "OP匿名版用户注册", "验证码: " + code);
        log.trace("验证码{}已发送至{}", code, email);
        return Result.success("send_captcha");
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<Integer> register(@RequestBody RegisterDto registerDto) {
        Result<Integer> result;
        // 邮箱是否已经被使用？
        AssertUtil.isEmpty(userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, registerDto.getEmail())), "邮箱已被使用");
        // 验证码是否正确？
        String realCaptcha = captchaService.getOne(new LambdaQueryWrapper<Captcha>()
                .eq(Captcha::getEmail, registerDto.getEmail())).getCaptcha();
        AssertUtil.equal(registerDto.getCaptcha(), realCaptcha, "验证码错误");
        result = Result.success("register");
        result.setData(userService.register(registerDto));
        return result;
    }
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<LoginResp> login(@RequestBody LoginDto loginDto) {
        User user = null;
        if (loginDto.getType().equals("email")) {
            user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, loginDto.getEmail()));
        } else if (loginDto.getType().equals("user_id")) {
            user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUser_id, loginDto.getUser_id()));
        }
        try {
            AssertUtil.isNotEmpty(user, "用户不存在");
            assert user != null;
            AssertUtil.equal(loginDto.getPassword(), user.getPassword(), "存在此用户，但密码错误");
            log.trace("用户{}登录成功", user.getUser_id());
//            eventPublisher.publishEvent(new UserLoginEvent(this, user.getUserId()));
        }catch (Exception e){
            return Result.error("login", e.getMessage());
        }
        Result<LoginResp> result;
        // 组装token
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getUser_id());
        String token = JwtUtil.generateToken(claims);
        // 组装data
        LoginResp resp = LoginResp.builder()
                .user_id(user.getUser_id())
                .biscuit_id(user.getBiscuit_id())
                .sex(user.getSex())
                .avatar(user.getAvatar())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .token(token)
                .build();
        result = Result.success("login");
        result.setData(resp);
        return result;
    }

}
