package com.mengya.dianping.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SellerModel {
    private Integer id;

    private String name;

    private Date createdAt;

    private Date updatedAt;

    private BigDecimal remarkScore;

    private Integer disabledFlag;
}