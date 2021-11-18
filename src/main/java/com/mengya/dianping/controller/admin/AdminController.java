package com.mengya.dianping.controller.admin;

import com.alibaba.druid.util.StringUtils;
import com.mengya.dianping.annotation.AdminPermission;
import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.common.CommonRes;
import com.mengya.dianping.common.EmBusinessError;
import com.mengya.dianping.constant.UserConstant;
import com.mengya.dianping.model.AdminModel;
import com.mengya.dianping.service.CategoryService;
import com.mengya.dianping.service.SellerService;
import com.mengya.dianping.service.ShopService;
import com.mengya.dianping.service.UserService;
import com.mengya.dianping.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName AdminController
 * @Description 运营后台用户登录
 * @Author xiongwei.wu
 * @Date 2021/4/20 14:31
 **/
@Controller
@RequestMapping("/admin/admin")
public class AdminController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private AdminModel adminModel;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;


    /**
     * 跳转到首页
     *
     * @return 返回用户数，用户名
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("admin/admin/index");

        //用户数
        modelAndView.addObject("userCount", userService.countAllUser());
        //门店数
        modelAndView.addObject("shopCount", shopService.countAllShop());

        //品类数
        modelAndView.addObject("categoryCount", categoryService.countAllCategory());
        //商户数
        modelAndView.addObject("sellerCount", sellerService.countAllSeller());
        modelAndView.addObject("CONTROLLER_NAME", "admin");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }

    /**
     * 模拟一个错误的json请求，@AdminPermission(produceType = "text/json")会返回json格式的错误代码
     *
     * @return CommonRes
     */
    @RequestMapping("/simulationFail")
    @AdminPermission(produceType = "text/json")
    public CommonRes simulationFail() {
        return CommonRes.create(null);
    }

    /**
     * 前往登录页面
     *
     * @return 登录页面
     */
    @RequestMapping("/loginPage")
    public ModelAndView loginPage() {
        return new ModelAndView("admin/admin/login");
    }


    /**
     * 登录表单验证
     *
     * @param email    邮箱
     * @param password 密码
     * @return true：重定向首页；false：给异常
     * @throws BusinessException        exception基类
     * @throws NoSuchAlgorithmException md5密码加解密异常
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(name = "email") String email,
                        @RequestParam(name = "password") String password) throws BusinessException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, EmBusinessError.ADMIN_NAME_PASSWORD_NOT_NULL.getErrMsg());
        }
        if (email.equals(adminModel.getEmail()) && CommonUtils.encoder(password).equals(adminModel.getEncryptPassword())) {
            //登录成功
            httpServletRequest.getSession().setAttribute(UserConstant.CURRENT_ADMIN_SESSION, email);
            return "redirect:/admin/admin/index";
        } else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, EmBusinessError.ADMIN_NAME_PASSWORD_LOGIN_FAIL.getErrMsg());
        }

    }
}
