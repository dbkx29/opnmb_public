package icu.dbkx.opnmb.common.pojo.vo.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResp {
    private Integer user_id;
    private String biscuit_id;
    private String email;
    private String nickname;
    private String token;
    private String avatar;
    private String sex;
}
