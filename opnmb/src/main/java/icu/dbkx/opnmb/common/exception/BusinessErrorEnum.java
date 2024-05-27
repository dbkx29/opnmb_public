package icu.dbkx.opnmb.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务校验异常码
 */
@AllArgsConstructor
@Getter
public enum BusinessErrorEnum {
    BUSINESS_ERROR(1001, "{0}"),
    SYSTEM_ERROR(1001, "系统出小差了，请稍后再试哦~~"),
    DUPLICATE_RECORD(1002, "重复记录"),
    PARAMETER_ERROR(1003, "参数错误");

    private final Integer code;
    private final String msg;
}
