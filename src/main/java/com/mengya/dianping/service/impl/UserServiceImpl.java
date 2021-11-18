package com.mengya.dianping.service.impl;

import com.mengya.dianping.common.BusinessException;
import com.mengya.dianping.common.EmBusinessError;
import com.mengya.dianping.constant.UserConstant;
import com.mengya.dianping.dal.UserModelMapper;
import com.mengya.dianping.model.UserModel;
import com.mengya.dianping.service.UserService;
import com.mengya.dianping.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @Description 用户接口服务实现
 * @Author xiongwei.wu
 * @Date 2021/4/19 11:57
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public UserModel registerUser(UserModel registerUser) throws BusinessException, NoSuchAlgorithmException {
        //加解密
        registerUser.setPassword(CommonUtils.encoder(registerUser.getPassword()));
        registerUser.setCreatedAt(new Date());
        registerUser.setUpdatedAt(new Date());

        try {
            userModelMapper.insertSelective(registerUser);
        } catch (DuplicateKeyException exception) {
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }

        return getUser(registerUser.getId());
    }

    @Override
    public UserModel loginUser(String telphone, String password)
            throws NoSuchAlgorithmException, BusinessException {
        //匹配用户
        UserModel userModel = userModelMapper.selectUserByTelPhoneAndPassword(telphone, CommonUtils.encoder(password));
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.LOGIN_FAIL);
        }
        return userModel;
    }

    @Override
    public Integer countAllUser() {
        return userModelMapper.countAllUser();
    }
}
