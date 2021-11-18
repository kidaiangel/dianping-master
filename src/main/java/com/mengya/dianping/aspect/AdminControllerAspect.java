package com.mengya.dianping.aspect;

import com.mengya.dianping.annotation.AdminPermission;
import com.mengya.dianping.common.CommonError;
import com.mengya.dianping.common.CommonRes;
import com.mengya.dianping.common.EmBusinessError;
import com.mengya.dianping.constant.CommonConstant;
import com.mengya.dianping.constant.UserConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName AdminControllerAspect
 * @Description 运营后台admin登录切面标记
 * @Author xiongwei.wu
 * @Date 2021/4/20 15:30
 **/
@Aspect
@Configuration
public class AdminControllerAspect {

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 环绕监听admin包下的资源，作用于标记了注解RequestMapping的方法上面
     *
     * @param joinPoint 切入点
     * @return Object
     * @throws Throwable
     */
    @Around("execution(* com.mengya.dianping.controller.admin.*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法函数
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //取方法上面标注了@AdminPermission的注解
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
        if (adminPermission == null) {
            //公共资源放行
            return joinPoint.proceed();
        }

        //判断当前管理员是否登录
        String email = (String) httpServletRequest.getSession().getAttribute(UserConstant.CURRENT_ADMIN_SESSION);
        if (email == null) {
            if (adminPermission.produceType().equals(UserConstant.TEXT_TYPE_HTML)) {
                //重定向
                httpServletResponse.sendRedirect("/admin/admin/loginPage");
                return null;
            } else {
                return CommonRes.create(new CommonError(EmBusinessError.ADMIN_SHOULD_LOGIN), CommonConstant.MESSAGE_STATUS_FAIL);
            }

        } else {
            return joinPoint.proceed();
        }
    }
}
