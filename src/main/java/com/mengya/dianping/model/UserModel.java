package com.mengya.dianping.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserModel {

    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private String telphone;

    private String password;

    private String nickName;

    private Integer gender;

}