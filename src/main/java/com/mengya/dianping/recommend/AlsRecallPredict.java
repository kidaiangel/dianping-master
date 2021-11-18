package com.mengya.dianping.recommend;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.ForeachPartitionFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AlsRecallPredict
 * @Description
 * @Author xiongwei.wu
 * @Date 2021/5/8 14:15
 **/
public class AlsRecallPredict {

    public static void main(String[] args) {
        //解决 HADOOP_HOME or hadoop.home.dir are not set
      //  System.setProperty("hadoop.home.dir", "D:\\Apache\\hadoop\\hadoop-common-2.2.0-bin-master");

        //初始化spark运行环境
        SparkSession spark = SparkSession.builder().master("local").appName("DianPingApp").getOrCreate();

        //加载模型进内存
        ALSModel alsModel = ALSModel.load("G:\\dianping-master\\src\\main\\resources\\alsmodel");

        JavaRDD<String> csvFile = spark.read().textFile("G:\\dianping-master\\src\\main\\resources\\behavior.csv").toJavaRDD();
        JavaRDD<AlsRecallTrain.Rating> ratingJavaRDD = csvFile.map(new Function<String, AlsRecallTrain.Rating>() {
            @Override
            public AlsRecallTrain.Rating call(String s) {
                return AlsRecallTrain.Rating.parseRating(s);
            }
        });
        Dataset<Row> rating = spark.createDataFrame(ratingJavaRDD, AlsRecallTrain.Rating.class);
        //给五个用户做离线的召回结果预测
        Dataset<Row> users = rating.select(alsModel.getUserCol()).distinct()
               // .limit(5)
                ;

        Dataset<Row> userRecs = alsModel.recommendForUserSubset(users, 200);

        userRecs.foreachPartition(new ForeachPartitionFunction<Row>() {
            @Override
            public void call(Iterator<Row> iterator) throws Exception {

                //新建数据连接
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://1.13.102.49:3306/dianping?user=root&password=ford123456&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
                PreparedStatement preparedStatement = connection.prepareStatement("insert into recommend(id,recommend) values (?,?)");
                List<Map<String, Object>> data = new ArrayList<>();
                iterator.forEachRemaining(action -> {
                    int userId = action.getInt(0);
                    List<GenericRowWithSchema> recommendationList = action.getList(1);
                    List<Integer> shopIdList = new ArrayList<>();
                    recommendationList.forEach(row -> {
                        Integer shopId = row.getInt(0);
                        shopIdList.add(shopId);
                    });
                    String recommendData = StringUtils.join(shopIdList, ",");
                    Map<String, Object> map = new HashMap<>();
                    map.put("userId", userId);
                    map.put("recommend", recommendData);
                    data.add(map);
                });
                data.forEach(stringObjectMap -> {
                    try {
                        preparedStatement.setInt(1, (Integer) stringObjectMap.get("userId"));
                        preparedStatement.setString(2, (String) stringObjectMap.get("recommend"));
                        preparedStatement.addBatch();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
                preparedStatement.executeBatch();
                connection.close();
            }
        });
    }

}


