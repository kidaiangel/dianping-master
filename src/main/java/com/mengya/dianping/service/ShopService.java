package com.mengya.dianping.service;

import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.model.ShopModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShopService
 * @Description 门店服务接口定义接口
 * @Author xiongwei.wu
 * @Date 2021/4/21 8:49
 **/
public interface ShopService {

    /**
     * 创建门店
     *
     * @param shopModel 门店模型
     * @return ShopModel
     * @throws BusinessException exception基类
     */
    ShopModel create(ShopModel shopModel) throws BusinessException;

    /**
     * 根据id获取门店信息
     *
     * @param id 门店id
     * @return ShopModel
     */
    ShopModel get(Integer id);

    /**
     * 获取所有门店
     *
     * @return List<ShopModel>
     */
    List<ShopModel> selectAll();

    /**
     * 推荐服务
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return List<ShopModel>
     */
    List<ShopModel> recommend(BigDecimal longitude, BigDecimal latitude);

    /**
     * 按标签分组
     *
     * @param keyword    关键字
     * @param categoryId 品类
     * @param tags       标签
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> searchGroupByTags(String keyword, Integer categoryId, String tags);

    /**
     * 获取门店总数
     *
     * @return sum
     */
    Integer countAllShop();

    /**
     * 搜索服务
     *
     * @param longitude  经度
     * @param latitude   纬度
     * @param keyword    关键字
     * @param orderby    排序
     * @param categoryId 品类id
     * @param tags       标签
     * @return List<ShopModel>
     */
    List<ShopModel> search(BigDecimal longitude, BigDecimal latitude,
                           String keyword, Integer orderby, Integer categoryId, String tags);

    /**
     * 搜索服务，使用es
     *
     * @param longitude  经度
     * @param latitude   纬度
     * @param keyword    关键字
     * @param orderby    排序
     * @param categoryId 品类id
     * @param tags       标签
     * @return Map<String, Object>
     * @throws IOException io exception
     */
    Map<String, Object> searchEs(BigDecimal longitude, BigDecimal latitude,
                                 String keyword, Integer orderby, Integer categoryId, String tags) throws IOException;

    /**
     * 构建es数据
     *
     * @param sellerId   商户id
     * @param shopId     门店id
     * @param categoryId 品类id
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> buildEsQuery(Integer sellerId, Integer shopId, Integer categoryId);
}
