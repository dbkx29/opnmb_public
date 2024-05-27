package icu.dbkx.opnmb.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import icu.dbkx.opnmb.common.pojo.Result;
import icu.dbkx.opnmb.common.utils.AssertUtil;
import icu.dbkx.opnmb.common.utils.BiscuitUtil;
import icu.dbkx.opnmb.common.utils.RequestContext;
import icu.dbkx.opnmb.generator.entity.Biscuit;
import icu.dbkx.opnmb.generator.entity.User;
import icu.dbkx.opnmb.generator.service.BiscuitService;
import icu.dbkx.opnmb.generator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/biscuit")
@Slf4j
public class BiscuitController {
    @Resource
    UserService userService;
    @Resource
    BiscuitService biscuitService;

    /**
     * 领取饼干
     */
    @GetMapping("/get")
    public Result<Biscuit> getBiscuit() {
        Integer user_id = RequestContext.getContext();
        AssertUtil.isNotEmpty(userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUser_id, user_id)), "用户不存在");
        log.trace("用户{}请求领取饼干", user_id);
        Biscuit biscuit;
        if (biscuitService.count(new LambdaQueryWrapper<Biscuit>()
                .eq(Biscuit::getUser_id, user_id)
                .eq(Biscuit::getActivate, 1)) >= 5) {
            return Result.error("/api/biscuit/get", "每人最多领取5个饼干");
        } else {
            biscuit = biscuitService.createBiscuit(user_id);
        }
        Result<Biscuit> result = Result.success("/api/biscuit/get");
        result.setData(biscuit);
        log.trace("用户{}领取饼干{}", user_id, biscuit.getBiscuit_id());
        return result;
    }

    /**
     * 查询名下所有饼干
     */
    @GetMapping("/list")
    public Result<List<Biscuit>> listBiscuit() {
        Integer user_id = RequestContext.getContext();
        Result<List<Biscuit>> result = Result.success("/api/biscuit/list");
        result.setData(biscuitService.list(new LambdaQueryWrapper<Biscuit>().eq(Biscuit::getUser_id, user_id).eq(Biscuit::getActivate, 1)));
        return result;
    }

    /**
     * 删除饼干
     */
    @DeleteMapping("/delete")
    public Result<String> deleteBiscuit(@RequestParam String biscuit_id) {
        Integer user_id = RequestContext.getContext();
        Biscuit biscuit = biscuitService.getOne(new LambdaQueryWrapper<Biscuit>().eq(Biscuit::getBiscuit_id, biscuit_id));
        if (biscuit == null) {
            return Result.error("/api/biscuit/delete", "饼干不存在");
        } else if (!biscuit.getUser_id().equals(user_id)) {
            return Result.error("/api/biscuit/delete", "无权删除他人的饼干");
        } else if (biscuit.getActivate() == 0) {
            return Result.error("/api/biscuit/delete", "饼干已删除");
        } else if (biscuitService.count(new LambdaQueryWrapper<Biscuit>().eq(Biscuit::getUser_id, user_id).eq(Biscuit::getActivate, 1)) <= 1) {
            return Result.error("/api/biscuit/delete", "至少保留一个饼干");
        } else if (biscuitService.count(new LambdaQueryWrapper<Biscuit>().eq(Biscuit::getUser_id, user_id).eq(Biscuit::getActivate, 0)) >= 3) {
            return Result.error("/api/biscuit/delete", "您已经删除了3个饼干，无法再删除");
        } else {
            biscuit.setActivate(0);
            biscuitService.update(biscuit, new LambdaQueryWrapper<Biscuit>().eq(Biscuit::getBiscuit_id, biscuit_id));
            return Result.success("/api/biscuit/delete");
        }
    }

    /**
     * 切换饼干
     */
    @PostMapping("/switch")
    public Result<String> switchBiscuit(@RequestParam String biscuit_id) {
        Integer user_id = RequestContext.getContext();
        Biscuit biscuit = biscuitService.getOne(new LambdaQueryWrapper<Biscuit>().eq(Biscuit::getBiscuit_id, biscuit_id));
        if (biscuit == null) {
            return Result.error("/api/biscuit/switch", "饼干不存在");
        } else if (!biscuit.getUser_id().equals(user_id)) {
            return Result.error("/api/biscuit/switch", "无权切换他人的饼干");
        } else {
            User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUser_id, user_id));
            user.setBiscuit_id(biscuit_id);
            userService.update(user, new LambdaQueryWrapper<User>().eq(User::getUser_id, user_id));
            return Result.success("/api/biscuit/switch");
        }
    }
}
