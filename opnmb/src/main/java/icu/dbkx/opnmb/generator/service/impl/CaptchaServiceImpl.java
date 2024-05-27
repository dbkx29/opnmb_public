package icu.dbkx.opnmb.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.dbkx.opnmb.generator.entity.Captcha;
import icu.dbkx.opnmb.generator.service.CaptchaService;
import icu.dbkx.opnmb.generator.mapper.CaptchaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
* @author Yumei
* @description 针对表【nmb_captcha】的数据库操作Service实现
* @createDate 2024-05-20 14:27:14
*/
@Service
@Slf4j
public class CaptchaServiceImpl extends ServiceImpl<CaptchaMapper, Captcha>
    implements CaptchaService {

    @Value("${spring.mail.username}")
    String from;
    @Autowired
    JavaMailSender mailSender;

    @Async
    public void sendCaptcha(String to,String subject,String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        log.trace(mailMessage.toString());
        mailSender.send(mailMessage);
    }
}




