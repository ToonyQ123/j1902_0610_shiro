package com.qf.j1902.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice//标识为控制层拦截器
public class ExceptionController {
    @ExceptionHandler(value = UnauthorizedException.class)//标识需要拦截的异常
    public String defaultErrorHandler(HttpServletRequest request, Exception e){
        return "unauth";
    }
}
