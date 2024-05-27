package icu.dbkx.opnmb.common.exception;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import icu.dbkx.opnmb.common.pojo.Result;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 业务校验异常码
 */
@AllArgsConstructor
@Getter
public enum HttpErrorEnum {

    ACCESS_DENIED(401, "您还未登录，请登录后再试"),
    TOKEN_NOT_FOUND(401,"未找到token"),
    TOKEN_INVALID(401,"不是可以识别的token"),
    NOT_FOUND(404, "未找到资源"),
    ;
    private final Integer httpCode;
    private final String msg;

    public void sendHttpError(HttpServletResponse response) throws IOException {
        response.setStatus(this.getHttpCode());
        response.setContentType(ContentType.JSON.toString(StandardCharsets.UTF_8));

        Result<String> responseData = Result.error(this.getMsg());
        response.getWriter().write(JSONUtil.toJsonStr(responseData));
    }
}
