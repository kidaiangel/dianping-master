package com.mengya.dianping.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserRsp
 * @Description 用户响应对象 实现了网络序列化端口
 * @Author xiongwei.wu
 * @Date 2021/4/20 10:59
 **/
@Data
public class UserResp implements Serializable {

    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private String telphone;

    private String password;

    private String nickName;

    private Integer gender;

}
