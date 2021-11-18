package com.mengya.dianping.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ElasticSearchRestClient
 * @Description 连接es服务
 * @Author xiongwei.wu
 * @Date 2021/4/26 14:12
 **/
@Configuration
public class ElasticSearchRestClient {

    @Value("${elasticsearch.ip}")
    private String ipAddress;

    @Bean("restHighLevelClient")
    public RestHighLevelClient getRestHighLevelClient() {
        String[] host = ipAddress.split(":");
        String ip = host[0];
        int port = Integer.parseInt(host[1]);
        HttpHost httpHost = new HttpHost(ip, port, "http");
        return new RestHighLevelClient(RestClient.builder(httpHost));
    }
}
