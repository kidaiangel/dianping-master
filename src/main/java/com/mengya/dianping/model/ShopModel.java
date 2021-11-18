package com.mengya.dianping.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ShopModel
 * @Description 门店模型
 * @Author xiongwei.wu
 * @Date 2021/4/21 10:44
 **/
@Data
public class ShopModel {

    /**
     * 门店id
     */
    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 评分
     */
    private BigDecimal remarkScore;

    /**
     * 人均消费
     */
    private Integer pricePerMan;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 距离计算公式
     * ceil(1 + 1000*(2 * 6378.137* ASIN(SQRT(POW(SIN(PI() * (#{latitude} - latitude) / 360), 2) + COS(PI() * #{latitude} / 180) * COS(latitude* PI() / 180) * POW(SIN(PI() * (#{longitude} - longitude) / 360), 2))))) AS distance
     */
    private Integer distance;

    /**
     * 品类id
     */
    private Integer categoryId;

    /**
     * 标签
     */
    private String tags;

    private String startTime;

    private String endTime;

    /**
     * 地址
     */
    private String address;

    private Integer sellerId;

    /**
     * 图片
     */
    private String iconUrl;

    private SellerModel sellerModel;

    private CategoryModel categoryModel;
}