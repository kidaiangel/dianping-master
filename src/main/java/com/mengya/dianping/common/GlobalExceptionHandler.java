package com.mengya.dianping.common;

import com.mengya.dianping.constant.CommonConstant;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 切面处理 --处理controller层返回的exception
 * @Author xiongwei.wu
 * @Date 2021/4/19 17:30
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常处理 监听异常发生后异常处理流程
     *
     * @param request   请求对象
     * @param response  响应对象
     * @param exception 异常
     * @return CommonRes 通用返回消息处理类
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonRes doError(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        if (exception instanceof BusinessException) {
            return CommonRes.create(((BusinessException) exception).getCommonError(), CommonConstant.MESSAGE_STATUS_FAIL);
        } else if (exception instanceof NoHandlerFoundException) {
            return CommonRes.create(new CommonError(EmBusinessError.NO_HANDLER_FOUND), CommonConstant.MESSAGE_STATUS_FAIL);
        } else if (exception instanceof ServletRequestBindingException) {
            return CommonRes.create(new CommonError(EmBusinessError.BIND_EXCEPTION_ERROR), CommonConstant.MESSAGE_STATUS_FAIL);
        } else {
            return CommonRes.create(new CommonError(EmBusinessError.UNKNOWN_ERROR), CommonConstant.MESSAGE_STATUS_FAIL);
        }
    }

}
