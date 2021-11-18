package com.mengya.dianping.controller;

import com.alibaba.druid.util.StringUtils;
import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.common.CommonRes;
import com.mengya.dianping.common.EmBusinessError;
import com.mengya.dianping.model.CategoryModel;
import com.mengya.dianping.model.ShopModel;
import com.mengya.dianping.service.CategoryService;
import com.mengya.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShopController
 * @Description app门店服务rest api 服务接口类
 * @Author xiongwei.wu
 * @Date 2021/4/21 10:44
 **/
@Controller("/shop")
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;


    /**
     * app首页 猜你喜欢 推荐服务v1.0
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return CommonRes
     */
    @RequestMapping("/recommend")
    @ResponseBody
    public CommonRes recommend(@RequestParam("longitude") BigDecimal longitude,
                               @RequestParam("latitude") BigDecimal latitude) throws BusinessException {
        if (longitude == null || latitude == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<ShopModel> shopModels = shopService.recommend(longitude, latitude);
        return CommonRes.create(shopModels);
    }

    /**
     * app首页 关键字 搜索服务V1.0
     *
     * @param longitude  经度
     * @param latitude   纬度
     * @param keyword    关键字
     * @param orderby    排序
     * @param categoryId 品类id
     * @param tags       标签
     * @return CommonRes
     * @throws BusinessException exception基类
     */
    @RequestMapping("/search")
    @ResponseBody
    public CommonRes search(@RequestParam(name = "longitude") BigDecimal longitude,
                            @RequestParam(name = "latitude") BigDecimal latitude,
                            @RequestParam(name = "keyword") String keyword,
                            @RequestParam(name = "orderby", required = false) Integer orderby,
                            @RequestParam(name = "categoryId", required = false) Integer categoryId,
                            @RequestParam(name = "tags", required = false) String tags) throws BusinessException, IOException {
        if (StringUtils.isEmpty(keyword) || longitude == null || latitude == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

//        List<ShopModel> shopModelList = shopService.search(longitude, latitude, keyword, orderby, categoryId, tags);
        Map<String, Object> map = shopService.searchEs(longitude, latitude, keyword, orderby, categoryId, tags);
        List<ShopModel> shopModelList = (List<ShopModel>) map.get("shop");

        List<CategoryModel> categoryModelList = categoryService.selectAll();
//        List<Map<String, Object>> tagsAggregation = shopService.searchGroupByTags(keyword, categoryId, tags);
        List<Map<String, Object>> tagsAggregation = (List<Map<String, Object>>) map.get("tags");
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("shop", shopModelList);
        resMap.put("category", categoryModelList);
        resMap.put("tags", tagsAggregation);
        return CommonRes.create(resMap);

    }

}
