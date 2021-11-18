package com.mengya.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengya.dianping.annotation.AdminPermission;
import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.common.EmBusinessError;
import com.mengya.dianping.dto.request.PageQuery;
import com.mengya.dianping.dto.request.ShopCreateReq;
import com.mengya.dianping.model.ShopModel;
import com.mengya.dianping.service.ShopService;
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
 * @ClassName ShopController
 * @Description 运营后台门店管理controller
 * @Author xiongwei.wu
 * @Date 2021/4/21 9:02
 **/
@Controller
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    /**
     * 获取门店列表
     *
     * @param pageQuery 分页参数
     * @return ModelAndView
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        List<ShopModel> shopModelList = shopService.selectAll();
        PageInfo<ShopModel> shopModelPageInfo = new PageInfo<>(shopModelList);
        ModelAndView modelAndView = new ModelAndView("admin/shop/index.html");
        modelAndView.addObject("data", shopModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "shop");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }

    /**
     * 跳转到创建门店页面
     *
     * @return ModelAndView
     */
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("admin/shop/create.html");
        modelAndView.addObject("CONTROLLER_NAME", "shop");
        modelAndView.addObject("ACTION_NAME", "create");
        return modelAndView;
    }

    /**
     * 门店创建接口
     *
     * @param shopCreateReq 门店创建请求对象
     * @param bindingResult     springboot 校验返回结果集
     * @return String
     * @throws BusinessException exception基类
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid ShopCreateReq shopCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtils.processErrorString(bindingResult));
        }
        ShopModel shopModel = new ShopModel();
        BeanUtils.copyProperties(shopCreateReq, shopModel);

        shopService.create(shopModel);


        return "redirect:/admin/shop/index";


    }
}
