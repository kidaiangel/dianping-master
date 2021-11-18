package com.mengya.dianping.recommend.service.impl;

import com.mengya.dianping.dal.RecommendModelMapper;
import com.mengya.dianping.model.RecommendModel;
import com.mengya.dianping.recommend.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RecommendServiceImpl
 * @Description 推荐服务service实现类
 * @Author xiongwei.wu
 * @Date 2021/5/8 17:07
 **/
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendModelMapper recommendMapper;

    @Override
    public List<Integer> recall(Integer userId) {
        RecommendModel recommend = recommendMapper.selectByPrimaryKey(userId);
        if (recommend == null) {
            recommend = recommendMapper.selectByPrimaryKey(1434);
        }
        String[] shopIdArr = recommend.getRecommend().split(",");
        List<Integer> shopIdList = new ArrayList<>();
        for (int i = 0; i < shopIdArr.length; i++) {
            shopIdList.add(Integer.valueOf(shopIdArr[i]));
        }
        return shopIdList;
    }
}
