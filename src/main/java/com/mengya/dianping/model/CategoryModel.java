package com.mengya.dianping.model;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryModel {
    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private String name;

    private String iconUrl;

    private Integer sort;

}