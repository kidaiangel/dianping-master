package com.mengya.dianping.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName RegisterReq
 * @Description 注册用户请求对象
 * @Author xiongwei.wu
 * @Date 2021/4/20 10:01
 **/
@Data
public class RegisterReq {

    @NotBlank(message = "手机号码不能为空")
    private String telphone;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @NotNull(message = "性别不能为空")
    private Integer gender;

}
