package com.mengya.dianping.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName CategoryCreateReq
 * @Description 品类创建表单
 * @Author xiongwei.wu
 * @Date 2021/4/20 18:11
 **/
@Data
public class CategoryCreateReq {

    @NotBlank(message = "名字不能为空")
    private String name;

    @NotBlank(message = "iconUrl不能为空")
    private String iconUrl;

    @NotNull(message = "权重不能为空")
    private Integer sort;

}
