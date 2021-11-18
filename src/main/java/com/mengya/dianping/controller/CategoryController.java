package com.mengya.dianping.controller;

import com.mengya.dianping.common.CommonRes;
import com.mengya.dianping.model.CategoryModel;
import com.mengya.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Description app品类服务rest api接口提供类
 * @Author xiongwei.wu
 * @Date 2021/4/21 10:39
 **/
@Controller("/category")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    @ResponseBody
    public CommonRes list() {
        List<CategoryModel> categoryModels = categoryService.selectAll();
        return CommonRes.create(categoryModels);
    }
}
