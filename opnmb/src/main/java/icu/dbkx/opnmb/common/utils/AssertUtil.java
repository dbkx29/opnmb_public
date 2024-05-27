package icu.dbkx.opnmb.common.utils;

import cn.hutool.core.util.ObjectUtil;
import icu.dbkx.opnmb.common.exception.BusinessErrorEnum;
import icu.dbkx.opnmb.common.exception.BusinessException;

import java.text.MessageFormat;
import java.util.Objects;

public class AssertUtil {
    // 如果是空对象，则抛异常
    public static void isNotEmpty(Object obj, String msg) {
        if (isEmpty(obj)) {
            throwException(msg);
        }
    }

    // 如果不是空对象，则抛异常
    public static void isEmpty(Object obj, String msg) {
        if (!isEmpty(obj)) {
            throwException(msg);
        }
    }

    public static Boolean equal(Object o1, Object o2, String msg) {
        if (!ObjectUtil.equal(o1, o2)) {
            throwException(msg);
        }else{
            return true;
        }
        return false;
    }

    public static void notEqual(Object o1, Object o2, String msg) {
        if (ObjectUtil.equal(o1, o2)) {
            throwException(msg);
        }
    }

    private static boolean isEmpty(Object obj) {
        return ObjectUtil.isEmpty(obj);
    }

    private static void throwException(String msg) {
        throwException(null, msg);
    }

    private static void throwException(BusinessErrorEnum errorEnum, Object... arg) {
        if (Objects.isNull(errorEnum)) {
            errorEnum = BusinessErrorEnum.BUSINESS_ERROR;
        }
        throw new BusinessException(errorEnum.getCode(), MessageFormat.format(errorEnum.getMsg(), arg),new Throwable("Business Error"));
    }
}
