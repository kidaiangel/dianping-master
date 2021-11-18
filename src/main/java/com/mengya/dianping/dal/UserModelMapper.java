package com.mengya.dianping.dal;

import com.mengya.dianping.model.UserModel;
import org.apache.ibatis.annotations.Param;

public interface UserModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);

    /**
     * 根据用户手机号码和用户密码取用户信息
     *
     * @param telphone 手机号码
     * @param password 密码
     * @return UserModel
     */
    UserModel selectUserByTelPhoneAndPassword(@Param("telphone") String telphone, @Param("password") String password);

    /**
     * 获取用户数量
     *
     * @return Integer
     */
    Integer countAllUser();
}