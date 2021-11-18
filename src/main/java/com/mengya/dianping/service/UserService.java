package com.mengya.dianping.service;

import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.model.UserModel;

import java.security.NoSuchAlgorithmException;

/**
 * @ClassName UserService
 * @Description 用户服务接口
 * @Author xiongwei.wu
 * @Date 2021/4/19 11:54
 **/
public interface UserService {
    /**
     * 根据Id获取用户
     *
     * @param id 用户id
     * @return 返回用户model
     */
    UserModel getUser(Integer id);

    /**
     * 用户注册接口
     *
     * @param userModel 用户对象
     * @return 用户 UserModel
     * @throws BusinessException        基类exception
     * @throws NoSuchAlgorithmException MD5加解密
     */
    UserModel registerUser(UserModel userModel) throws BusinessException, NoSuchAlgorithmException;

    /**
     * 用户登录接口
     *
     * @param telphone 手机号码
     * @param password 密码
     * @return UserModel
     * @throws NoSuchAlgorithmException MD5加解密
     * @throws BusinessException        基类exception
     */
    UserModel loginUser(String telphone, String password) throws NoSuchAlgorithmException, BusinessException;

    /**
     * 获取用户数量
     *
     * @return int
     */
    Integer countAllUser();
}
