package com.mengya.dianping.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName AdminPermission
 * @Description 运营admin切面标记
 * @Author xiongwei.wu
 * @Date 2021/4/20 15:28
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminPermission {
    /**
     * 标记返回的文本类型
     * "text/html"
     * "text/json"
     *
     * @return 文本类型值
     */
    String produceType() default "text/html";
}
