package com.liu.donate.common;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/4 13:59
 * 统一异常处理类
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        e.printStackTrace();
        return Result.getFailure();
    }

}
