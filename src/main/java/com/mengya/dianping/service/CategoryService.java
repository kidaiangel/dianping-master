package com.mengya.dianping.service;

import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.model.CategoryModel;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Description 品类服务定义接口
 * @Author xiongwei.wu
 * @Date 2021/4/20 18:02
 **/
public interface CategoryService {
    /**
     * 品类创建
     *
     * @param categoryModel 品类对象
     * @return CategoryModel
     * @throws BusinessException exception基类
     */
    CategoryModel create(CategoryModel categoryModel) throws BusinessException;

    /**
     * 获取品类
     *
     * @param id 品类Id
     * @return CategoryModel
     */
    CategoryModel get(Integer id);

    /**
     * 查询全部品类
     *
     * @return List<CategoryModel>
     */
    List<CategoryModel> selectAll();

    /**
     * 获取所有的品类数量
     *
     * @return Integer
     */
    Integer countAllCategory();
}
