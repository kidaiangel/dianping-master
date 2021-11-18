package com.mengya.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengya.dianping.annotation.AdminPermission;
import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.common.CommonRes;
import com.mengya.dianping.common.EmBusinessError;
import com.mengya.dianping.constant.CommonConstant;
import com.mengya.dianping.dto.request.PageQuery;
import com.mengya.dianping.dto.request.SellerCreateReq;
import com.mengya.dianping.model.SellerModel;
import com.mengya.dianping.service.SellerService;
import com.mengya.dianping.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SellerController
 * @Description 商户服务restApi接口
 * @Author xiongwei.wu
 * @Date 2021/4/20 16:55
 **/
@Controller
@RequestMapping("/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * 商户列表
     *
     * @param pageQuery 分页参数
     * @return ModelAndView
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        List<SellerModel> sellerModelList = sellerService.selectAll();
        PageInfo<SellerModel> sellerModelPageInfo = new PageInfo<>(sellerModelList);
        ModelAndView modelAndView = new ModelAndView("admin/seller/index.html");
        modelAndView.addObject("data", sellerModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "seller");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }

    /**
     * 创建商家页面
     *
     * @return ModelAndView
     */
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("admin/seller/create.html");
        modelAndView.addObject("CONTROLLER_NAME", "seller");
        modelAndView.addObject("ACTION_NAME", "create");
        return modelAndView;
    }

    /**
     * 创建商家表单
     *
     * @param sellerCreateReq 商户创建对象
     * @param bindingResult   springboot 校验返回结果集
     * @return String
     * @throws BusinessException exception基类
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid SellerCreateReq sellerCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtils.processErrorString(bindingResult));
        }

        SellerModel sellerModel = new SellerModel();
        sellerModel.setName(sellerCreateReq.getName());
        sellerService.create(sellerModel);

        return "redirect:/admin/seller/index";


    }


    /**
     * 禁用商户
     *
     * @param id 商户Id
     * @return CommonRes
     * @throws BusinessException exception基类
     */
    @RequestMapping(value = "down", method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes down(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, CommonConstant.SELLER_DISABLED_FLAG_DOWN);
        return CommonRes.create(sellerModel);
    }

    /**
     * 启用商户
     *
     * @param id 商户id
     * @return CommonRes
     * @throws BusinessException exception基类
     */
    @RequestMapping(value = "up", method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes up(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, CommonConstant.SELLER_DISABLED_FLAG_UP);
        return CommonRes.create(sellerModel);
    }
}
