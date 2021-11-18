package com.mengya.dianping.recommend;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.io.Serializable;

/**
 * @ClassName AlsRecallTrain
 * @Description ALS召回算法的训练
 * @Author xiongwei.wu
 * @Date 2021/5/8 14:20
 **/
public class AlsRecallTrain implements Serializable {

    public static void main(String[] args) throws IOException {
        //解决 HADOOP_HOME or hadoop.home.dir are not set
        //System.setProperty("hadoop.home.dir", "D:\\Apache\\hadoop\\hadoop-common-2.2.0-bin-master");

        //初始化spark运行环境
        SparkSession spark = SparkSession.builder().master("local").appName("DianPingApp").getOrCreate();

        //读取csv文件
        JavaRDD<String> csvFile = spark.read().textFile("G:\\dianping-master\\src\\main\\resources\\behavior.csv").toJavaRDD();

        //定义数据结构
        JavaRDD<Rating> ratingJavaRDD = csvFile.map(new Function<String, Rating>() {
            /**
             * 将文件格式转换为Rating
             * @param s 字符串
             * @return Rating
             */
            @Override
            public Rating call(String s) {
                return Rating.parseRating(s);
            }
        });

        //扁平化数据，以rating的方式管理数据
        Dataset<Row> rating = spark.createDataFrame(ratingJavaRDD, Rating.class);

        //将所有的rating数据分成8 2份,20%用于机器学习训练，80%用于测试
        Dataset<Row>[] splits = rating.randomSplit(new double[]{0.8, 0.2});
        Dataset<Row> training = splits[0];
        Dataset<Row> testing = splits[1];

        //过拟合：增大数据规模，减少RANK，增大正则化的系数
        //欠拟合：增加RANK，减少正则化系数
        ALS als = new ALS().setMaxIter(10).setRank(5).setRegParam(0.01).setUserCol("userId").setItemCol("shopId").setRatingCol("rating");

        //模型训练
        ALSModel alsModel = als.fit(training);
        //模型评测
        Dataset<Row> predictions = alsModel.transform(testing);
        //rmse均方根误差，预测值和真实值的偏差的平方除以观测值，开个根号
        RegressionEvaluator evaluator = new RegressionEvaluator().setMetricName("rmse").setLabelCol("rating").setPredictionCol("prediction");
        double rmse = evaluator.evaluate(predictions);
        System.out.println("rmse = " + rmse);
//        alsModel.save("E:\\study project\\dianping\\src\\main\\resources\\alsmodel");
        alsModel.write().overwrite().save("G:\\dianping-master\\src\\main\\resources\\alsmodel");
    }
    public static class Rating implements Serializable {
        private int userId;
        private int shopId;
        private int rating;

        public static AlsRecallTrain.Rating parseRating(String str) {
            str = str.replace("\"", "");
            String[] strArr = str.split(",");
            int userId = Integer.parseInt(strArr[0]);
            int shopId = Integer.parseInt(strArr[1]);
            int rating = Integer.parseInt(strArr[2]);
            return new AlsRecallTrain.Rating(userId, shopId, rating);
        }

        public Rating(int userId, int shopId, int rating) {
            this.userId = userId;
            this.shopId = shopId;
            this.rating = rating;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }
    }

}
