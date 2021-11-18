package com.mengya.dianping.recommend.service;

import com.mengya.dianping.model.ShopSortModel;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName RecommendSortService
 * @Description TODO
 * @Author xiongwei.wu
 * @Date 2021/5/10 8:27
 **/
@Service
public class RecommendSortService {

    private SparkSession sparkSession;
    private LogisticRegressionModel lrModel;

    @PostConstruct
    public void init() {
        //解决 HADOOP_HOME or hadoop.home.dir are not set
       // System.setProperty("hadoop.home.dir", "D:\\Apache\\hadoop\\hadoop-common-2.2.0-bin-master");

        //加载LR模型
        SparkSession spark = SparkSession.builder().master("local").appName("DianPingApp").getOrCreate();
        lrModel = LogisticRegressionModel.load("/opt/lrmodel");
    }

    public List<Integer> sort(List<Integer> shopIdList, Integer userId) {
        //需要根据Irmodel所需要的11位的x，生成特征，然后调用其预测方法
        List<ShopSortModel> list = new ArrayList<>();
        for (Integer shopId : shopIdList) {
            //造的假数据，可以从数据库或缓存中拿到对应的性别，年龄，评分，价格等做特征转化生成feature向量
            Vector vector = Vectors.dense(1, 0, 0, 0, 0, 1, 0.6, 0, 0, 1, 0);
            Vector result = lrModel.predictProbability(vector);
            double[] arr = result.toArray();
            double score = arr[1];
            ShopSortModel shopSortModel = new ShopSortModel();
            shopSortModel.setShopId(shopId);
            shopSortModel.setScore(score);
            list.add(shopSortModel);
        }
        //正序
        list.sort(Comparator.comparingDouble(ShopSortModel::getScore));
        //倒序
        list.sort((o1, o2) -> Double.compare(o2.getScore(), o1.getScore()));
        return list.stream().map(ShopSortModel::getShopId).collect(Collectors.toList());
    }


}
