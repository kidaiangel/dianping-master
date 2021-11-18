package com.mengya.dianping.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName LoginReq
 * @Description 登录用户请求对象
 * @Author xiongwei.wu
 * @Date 2021/4/20 10:01
 **/
@Data
public class LoginReq {

    @NotBlank(message = "手机号码不能为空")
    private String telphone;

    @NotBlank(message = "密码不能为空")
    private String password;
}
