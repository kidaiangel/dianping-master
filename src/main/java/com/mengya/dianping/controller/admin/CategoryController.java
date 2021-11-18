package com.mengya.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengya.dianping.annotation.AdminPermission;
import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.common.EmBusinessError;
import com.mengya.dianping.dto.request.CategoryCreateReq;
import com.mengya.dianping.dto.request.PageQuery;
import com.mengya.dianping.model.CategoryModel;
import com.mengya.dianping.service.CategoryService;
import com.mengya.dianping.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName CategoryController
 * @Description 运营后台品类服务接口Rest API
 * @Author xiongwei.wu
 * @Date 2021/4/20 18:09
 **/
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取品类列表接口
     *
     * @param pageQuery 分页参数
     * @return ModelAndView
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        PageInfo<CategoryModel> categoryModelPageInfo = new PageInfo<>(categoryModelList);
        ModelAndView modelAndView = new ModelAndView("admin/category/index.html");
        modelAndView.addObject("data", categoryModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "category");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }

    /**
     * 跳转到创建品类页面
     *
     * @return ModelAndView
     */
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("admin/category/create.html");
        modelAndView.addObject("CONTROLLER_NAME", "category");
        modelAndView.addObject("ACTION_NAME", "create");
        return modelAndView;
    }

    /**
     * 品类创建接口
     *
     * @param categoryCreateReq 品类创建请求对象
     * @param bindingResult     springboot 校验返回结果集
     * @return String
     * @throws BusinessException exception基类
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid CategoryCreateReq categoryCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtils.processErrorString(bindingResult));
        }

        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryCreateReq, categoryModel);

        categoryService.create(categoryModel);

        return "redirect:/admin/category/index";


    }
}
