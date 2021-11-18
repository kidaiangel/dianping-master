package com.mengya.dianping.dal;

import com.mengya.dianping.model.SellerModel;

import java.util.List;

public interface SellerModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SellerModel record);

    int insertSelective(SellerModel record);

    SellerModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerModel record);

    int updateByPrimaryKey(SellerModel record);

    List<SellerModel> selectAll();

    Integer countAllSeller();
}