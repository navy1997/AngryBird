package com.birds.exception;

import com.birds.pojo.CodeMsg;
import com.birds.pojo.Result;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //该注解定义全局异常处理类
//@ControllerAdvice(basePackages ="com.example.demo.com.birds.controller") 可指定包
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value=Exception.class) //该注解声明异常处理方法
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        //对于自定义异常的处理
        if(e instanceof GlobalException) {
            GlobalException ex = (GlobalException)e;
            return Result.error(ex.getCm());
            //对于绑定异常的处理，使用jsr303中的自定义注解抛出的异常属于绑定异常
        }else if(e instanceof BindException) {

            return Result.error(CodeMsg.SERVER_ERROR);
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}