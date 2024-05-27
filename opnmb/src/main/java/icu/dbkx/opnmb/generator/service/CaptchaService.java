package icu.dbkx.opnmb.generator.service;

import icu.dbkx.opnmb.generator.entity.Captcha;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Yumei
* @description 针对表【nmb_captcha】的数据库操作Service
* @createDate 2024-05-20 14:27:14
*/
public interface CaptchaService extends IService<Captcha> {
    public void sendCaptcha(String to,String subject,String text);
}
