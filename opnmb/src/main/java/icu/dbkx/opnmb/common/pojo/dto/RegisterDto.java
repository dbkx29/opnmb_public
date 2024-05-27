package icu.dbkx.opnmb.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    String nickname;
    String password;
    String email;
    String captcha;
}
