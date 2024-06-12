package com.bench.interrupt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 用于处理所有类型的异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody  // 标识这个方法的返回值应直接作为 HTTP 响应体
    public BaseRes<Void> exceptionHandler(Exception e){
        log.error("request error", e);
        return BaseRes.error("系统异常");
    }

    // 自定义捕获处理绑定异常
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public BaseRes<Void> bindException(BindException e){
        // 获取绑定结果中的字段错误列表
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        // 将所有字段错误消息连接成一个字符串。
        String error = fieldErrors.stream().map(o -> o.getDefaultMessage())
                .collect(Collectors.joining());
        return BaseRes.error(error);
    }

    // 用于处理方法参数无效异常。
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseRes<Void> methodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String error = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining());
        return BaseRes.error(error);
    }

    // 算术运算时发生异常时
    @ExceptionHandler(value = ArithmeticException.class)
    @ResponseBody
    public BaseRes<Void> arithmeticException(ArithmeticException e){
        log.error("Arithmetic error: ", e);
        String error = e.getMessage();
        return BaseRes.error("算术错误: " + error);
    }

}
