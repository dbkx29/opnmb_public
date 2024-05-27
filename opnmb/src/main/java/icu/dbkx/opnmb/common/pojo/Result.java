package icu.dbkx.opnmb.common.pojo;

import lombok.Data;

@Data
public class Result<T> {
    String action;
    Integer code;
    String msg;
    T data;

    /**
     * 加入一个对象
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功响应，data为空
     * @param action
     * @return
     */
    public static <T> Result<T> success(String action){
        Result<T> result = new Result<T>();
        result.action = action;
        result.code = 200;
        result.msg = "success";
        result.data = null;
        return result;
    }
    public static <T> Result<T> success(String action,String msg){
        Result<T> result = new Result<T>();
        result.action = action;
        result.code = 200;
        result.msg = msg;
        result.data = null;
        return result;
    }

    /**
     * 异常响应，data为空
     * @param action
     * @return
     */
    public static <T> Result<T> error(String action){
        Result<T> result = new Result<T>();
        result.action = action;
        result.code = -1;
        result.msg = "error";
        result.data = null;
        return result;
    }
    public static <T> Result<T> error(String action,String msg){
        Result<T> result = new Result<T>();
        result.action = action;
        result.code = -1;
        result.msg = msg;
        result.data = null;
        return result;
    }
    public static <T> Result<T> error(String action,String msg,T data){
        Result<T> result = new Result<T>();
        result.action = action;
        result.code = -1;
        result.msg = msg;
        result.data = data;
        return result;
    }

    /**
     * 异常响应，权限不足
     * @param action
     * @return
     * @param <T>
     */
    public static <T> Result<T> noPermission(String action) {
        Result<T> result = new Result<T>();
        result.action = action;
        result.code = 403;
        result.msg = "no permission";
        result.data = null;
        return result;
    }
    public static <T> Result<T> noPermission(String action,String msg) {
        Result<T> result = new Result<T>();
        result.action = action;
        result.code = 403;
        result.msg = msg;
        result.data = null;
        return result;
    }
}
