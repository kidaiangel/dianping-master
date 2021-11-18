package com.mengya.dianping.model;

import lombok.Data;

/**
 * @ClassName ShopSortModel
 * @Description 商户排序模型
 * @Author xiongwei.wu
 * @Date 2021/5/10 8:31
 **/
@Data
public class ShopSortModel {
    private Integer shopId;
    private double score;
}
