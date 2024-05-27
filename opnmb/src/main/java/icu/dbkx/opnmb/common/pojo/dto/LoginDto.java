package icu.dbkx.opnmb.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * type,email,user_id,password
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    String type;
    String email;
    Integer user_id;
    String password;
}
