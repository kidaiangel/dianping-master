package com.mengya.dianping.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @ClassName CanalClient
 * @Description 连接canal服务的canal client客户端
 * @Author xiongwei.wu
 * @Date 2021/5/7 10:26
 **/
//@Component
//public class CanalClient implements DisposableBean {
//
//    @Value("${canal.hostname}")
//    private String canalHostName;
//
//    @Value("${canal.port}")
//    private int canalPort;
//
//    @Value("${canal.destination}")
//    private String canalDestination;
//
//    @Value("${canal.username}")
//    private String canalUserName;
//
//    @Value("${canal.password}")
//    private String canalPassword;
//
//    private CanalConnector canalConnector;
//
//    @Bean
//    public CanalConnector getCanalConnector() {
//        canalConnector = CanalConnectors.newClusterConnector(
//                Lists.newArrayList(new InetSocketAddress(canalHostName, canalPort)),
//                canalDestination,
//                canalUserName,
//                canalPassword
//
//        );
//        canalConnector.connect();
//        //指定filter,格式{database}.{table}
//        canalConnector.subscribe();
//        //回滚寻找上次中断的为止
//        canalConnector.rollback();
//
//        return canalConnector;
//    }
//
//
//    /**
//     * 在spring容器销毁的时候，需要断开canal客户端的连接
//     * 防止canal泄露
//     *
//     * @throws Exception 异常
//     */
//    @Override
//    public void destroy() throws Exception {
//        if (canalConnector != null) {
//            canalConnector.disconnect();
//        }
//    }
//}
