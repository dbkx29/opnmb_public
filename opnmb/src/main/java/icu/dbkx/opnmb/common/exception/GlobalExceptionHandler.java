package icu.dbkx.opnmb.common.exception;

import lombok.extern.slf4j.Slf4j;
import icu.dbkx.opnmb.common.pojo.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获validation参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValid Exception. The reason is: {}", e.getMessage());
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(err ->
                errorMsg.append(err.getField())
                        .append(err.getDefaultMessage())
                        .append("，")
        );
        String msg = errorMsg.toString();
        // 组装通用的数据返回格式（异常情况下）
        return Result.error(msg.substring(0, msg.length() - 1));
    }

    /**
     * 捕获自定义业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result<?> businessExceptionHandler(BusinessException e) {
        log.info("Custom Business Exception. The reason is: {}", e.getMessage());
//        return Result.error(e.getErrorMsg());
        return Result.error("",e.getMessage());
    }

    /**
     * 其他异常（系统内部的）
     */
    @ExceptionHandler(value = Throwable.class)
    public Result<?> throwableHandler(Throwable e) {
        log.error("System Exception. The reason is: {}", e.getMessage(), e);
        return Result.error("",e.getMessage());
    }
}
