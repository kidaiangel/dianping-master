package com.mengya.dianping.controller;

import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.common.CommonRes;
import com.mengya.dianping.common.EmBusinessError;
import com.mengya.dianping.constant.CommonConstant;
import com.mengya.dianping.constant.UserConstant;
import com.mengya.dianping.dto.request.LoginReq;
import com.mengya.dianping.dto.request.RegisterReq;
import com.mengya.dianping.dto.response.UserResp;
import com.mengya.dianping.model.UserModel;
import com.mengya.dianping.service.UserService;
import com.mengya.dianping.util.CommonUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName UserController
 * @Description 用户信息
 * @Author xiongwei.wu
 * @Date 2021/4/19 11:51
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("name", "mengya");
        return modelAndView;
    }

    /**
     * rest 提供给前端调用的getUser方法，根据userId获取user信息
     *
     * @param id userId
     * @return CommonRes 通用返回消息处理类
     * @throws BusinessException
     */
    @RequestMapping("/get")
    @ResponseBody
    public CommonRes getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUser(id);
        if (userModel == null) {
//            return CommonRes.create(new CommonError(EmBusinessError.NO_OBJECT_FOUND), "fail");
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
        } else {
            return CommonRes.create(userModel);
        }
    }

    /**
     * 用户注册接口
     *
     * @param registerReq   注册请求对象
     * @param bindingResult springboot 校验返回结果集
     * @return 用户 UserModel
     * @throws BusinessException        基类exception
     * @throws NoSuchAlgorithmException MD5加解密
     */
    @RequestMapping("/register")
    @ResponseBody
    public CommonRes registerUser(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult)
            throws BusinessException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtils.processErrorString(bindingResult));
        }
        UserModel userModel = new UserModel();
        //赋值
        BeanUtils.copyProperties(registerReq, userModel);
        //调用注册接口服务
        UserModel registerUser = userService.registerUser(userModel);
        return CommonRes.create(registerUser);
    }

    /**
     * 用户登录接口
     *
     * @param loginReq      登录请求对象
     * @param bindingResult springboot 校验返回结果集
     * @return 用户 UserModel
     * @throws BusinessException        基类exception
     * @throws NoSuchAlgorithmException MD5加解密
     */
    @RequestMapping("/login")
    @ResponseBody
    public CommonRes loginUser(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult)
            throws BusinessException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtils.processErrorString(bindingResult));
        }
        UserResp userResp = new UserResp();
        //登录
        UserModel loginUser = userService.loginUser(loginReq.getTelphone(), loginReq.getPassword());
        //赋值
        BeanUtils.copyProperties(loginUser, userResp);
        //存入session
        httpServletRequest.getSession().setAttribute(UserConstant.CURRENT_USER_SESSION, userResp);
        return CommonRes.create(userResp);
    }

    /**
     * 用户注销
     *
     * @return CommonRes
     * @throws BusinessException 基类exception
     */
    @RequestMapping("/logout")
    @ResponseBody
    public CommonRes logoutUser() throws BusinessException {
        //取当前登录用户
        CommonRes commonRes = getCurrentUserInfo(UserConstant.CURRENT_USER_SESSION);
        if (CommonConstant.MESSAGE_STATUS_SUCCESS.equals(commonRes.getStatus())) {
            //置为无效
            httpServletRequest.getSession().invalidate();
        }
        return CommonRes.create(null);
    }

    /**
     * 根据会话名称取用户session信息
     *
     * @param sessionName 会话名称
     * @return CommonRes
     * @throws BusinessException 基类exception
     */
    @RequestMapping("/getCurrentUserInfo")
    @ResponseBody
    public CommonRes getCurrentUserInfo(@Param("sessionName") String sessionName) throws BusinessException {
        UserResp userResp = (UserResp) httpServletRequest.getSession().getAttribute(sessionName);
        if (userResp == null) {
            throw new BusinessException(EmBusinessError.SESSION_HAVE_EXPIRED);
        }
        return CommonRes.create(userResp);
    }

    /**
     * 取用户信息 currentUserSession
     *
     * @return CommonRes
     */
    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public CommonRes getCurrentUserInfo() {
        UserResp userResp = (UserResp) httpServletRequest.getSession().getAttribute(UserConstant.CURRENT_USER_SESSION);
        return CommonRes.create(userResp);
    }
}
