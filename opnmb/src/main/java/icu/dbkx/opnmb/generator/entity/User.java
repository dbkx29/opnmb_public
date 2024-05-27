package icu.dbkx.opnmb.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import icu.dbkx.opnmb.common.pojo.dto.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName nmb_user
 */
@TableName(value ="nmb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer user_id;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private String biscuit_id;

    /**
     * 
     */
    private String personal_note;

    /**
     * 
     */
    private String sex;

    /**
     * 
     */
    private String avatar;

    /**
     * 
     */
    private LocalDateTime register_date;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private String notes;

    /**
     * 
     */
    private String captcha;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    public User(RegisterDto registerDto){
        this.nickname = registerDto.getNickname();
        this.password = registerDto.getPassword();
        this.email = registerDto.getEmail();
        this.captcha = registerDto.getCaptcha();
        this.avatar = "Akkarin.jpg";
    }
}