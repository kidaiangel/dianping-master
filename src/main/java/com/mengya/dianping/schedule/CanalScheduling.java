package com.mengya.dianping.schedule;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.google.common.collect.Lists;
import com.google.protobuf.InvalidProtocolBufferException;
import com.mengya.dianping.constant.CommonConstant;
import com.mengya.dianping.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CanalScheduling
 * @Description canal定时任务
 * @Author xiongwei.wu
 * @Date 2021/5/7 10:47
 **/
//@Slf4j
//@Component
//public class CanalScheduling implements Runnable, ApplicationContextAware {
//
//    private ApplicationContext applicationContext;
//
//    @Resource
//    private CanalConnector canalConnector;
//
//    @Autowired
//    private ShopService shopService;
//
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;

    /**
     * 设置定时任务，每一百毫秒执行一次任务
     */
//    @Override
//    @Scheduled(fixedDelay = 100)
//    public void run() {
//        long batchId = -1;
//        try {
//            //每次拉取条数
//            int batchSize = 1000;
//            Message message = canalConnector.getWithoutAck(batchSize);
//            //批次Id
//            batchId = message.getId();
//            List<CanalEntry.Entry> entries = message.getEntries();
//            if (batchId != -1 && entries.size() > 0) {
//                entries.forEach(entry -> {
//                    //MySQL种my.cnf中配置的是binlog_format = ROW，这里只解析ROW类型
//                    if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
//                        //解析处理
//                        publishCanalEvent(entry);
//                    }
//                });
//            }
//
//            //确认批次
//            canalConnector.ack(batchId);
//        } catch (CanalClientException e) {
//            e.printStackTrace();
//            //异常则回滚
//            canalConnector.rollback(batchId);
//        }
//    }
//
//    /**
//     * 处理管道数据事件
//     *
//     * @param entry 条目
//     */
//    private void publishCanalEvent(CanalEntry.Entry entry) {
//        //logBin 的类型
////        CanalEntry.EntryType entryType = entry.getEntryType();
//
//        //数据库
//        String database = entry.getHeader().getSchemaName();
//        //表
//        String table = entry.getHeader().getTableName();
//
//        CanalEntry.RowChange rowChange = null;
//        try {
//            //行改变值，受影响的行数
//            rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//            return;
//        }
//        rowChange.getRowDatasList().forEach(rowData -> {
//            //获取改变前的数据
//            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
//            beforeColumnsList.forEach(column -> {
//                log.info("更改前的数据| name:{} - value:{}", column.getName(), column.getValue());
//            });
//
//            //改变后的数据
//            List<CanalEntry.Column> columns = rowData.getAfterColumnsList();
////            String primaryKey = "id";
////            CanalEntry.Column idColumn = columns.stream().filter(column -> column.getIsKey()
////                    && primaryKey.equals(column.getName())).findFirst().orElse(null);
//            //转换格式
//            Map<String, Object> dataMap = parseColumnsToMap(columns);
//            log.info("更改后的数据:{}", dataMap);
//
//            //更新es
//            try {
//                indexEs(dataMap, database, table);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    /**
//     * 将处理的消息发送到es更新
//     *
//     * @param dataMap  转换后的map
//     * @param database 数据库
//     * @param table    表
//     */
//    private void indexEs(Map<String, Object> dataMap, String database, String table) throws IOException {
//        if (!StringUtils.equals(CommonConstant.DATABASE_DP, database)) {
//            return;
//        }
//        List<Map<String, Object>> results = Lists.newArrayList();
//        switch (table) {
//            case CommonConstant.TABLE_SELLER:
//                results = shopService.buildEsQuery(Integer.parseInt((String) dataMap.get("id")), null, null);
//                break;
//            case CommonConstant.TABLE_SHOP:
//                results = shopService.buildEsQuery(null, Integer.parseInt((String) dataMap.get("id")), null);
//                break;
//            case CommonConstant.TABLE_CATEGORY:
//                results = shopService.buildEsQuery(null, null, Integer.parseInt((String) dataMap.get("id")));
//                break;
//            default:
//                log.info("other {} table", table);
//                break;
//        }
//        if (!CollectionUtils.isEmpty(results)) {
//            for (Map<String, Object> map : results) {
//                IndexRequest indexRequest = new IndexRequest("shop");
//                indexRequest.id(String.valueOf(map.get("id")));
//                indexRequest.source(map);
//                restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//            }
//        }
//    }
//
//    /**
//     * 将列集合转化为Map集合
//     *
//     * @param columns 列集合
//     * @return Map<String, Object>
//     */
//    private Map<String, Object> parseColumnsToMap(List<CanalEntry.Column> columns) {
//        Map<String, Object> jsonMap = new HashMap<>();
//        columns.forEach(column -> {
//            if (column == null) {
//                return;
//            }
////            log.info("更改后的数据| name:{} - value:{}", column.getName(), column.getValue());
//            jsonMap.put(column.getName(), column.getValue());
//        });
//        return jsonMap;
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//}
