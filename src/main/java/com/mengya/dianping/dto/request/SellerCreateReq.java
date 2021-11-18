package com.mengya.dianping.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName SellerCreateReq
 * @Description 商户创建对象
 * @Author xiongwei.wu
 * @Date 2021/4/20 17:01
 **/
@Data
public class SellerCreateReq {
    @NotBlank(message = "商户名不能为空")
    private String name;
}
