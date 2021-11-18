package com.mengya.dianping.service;

import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.model.SellerModel;

import java.util.List;

/**
 * @ClassName SellerService
 * @Description 商户服务接口定义
 * @Author xiongwei.wu
 * @Date 2021/4/20 16:49
 **/
public interface SellerService {
    /**
     * 商户创建
     *
     * @param sellerModel 商户model
     * @return SellerModel
     */
    SellerModel create(SellerModel sellerModel);

    /**
     * 根据id获取商户信息
     *
     * @param id 商户id
     * @return SellerModel
     */
    SellerModel get(Integer id);

    /**
     * 查询全部的商家
     *
     * @return
     */
    List<SellerModel> selectAll();

    /**
     * 禁用或启用商家
     *
     * @param id           商家id
     * @param disabledFlag 值标志
     * @return SellerModel
     * @throws BusinessException exception基类
     */
    SellerModel changeStatus(Integer id, Integer disabledFlag) throws BusinessException;

    /**
     * 获取商户总数
     *
     * @return 总数
     */
    Integer countAllSeller();
}
