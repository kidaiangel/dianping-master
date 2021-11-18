package com.mengya.dianping.recommend.service;

import java.util.List;

/**
 * @ClassName RecommendService
 * @Description 推荐服务service
 * @Author xiongwei.wu
 * @Date 2021/5/8 17:05
 **/
public interface RecommendService {
    /**
     * 召回数据
     * 根据userId 召回shopIdList
     *
     * @param userId 用户Id
     * @return 召回shopIdList
     */
    List<Integer> recall(Integer userId);
}
