package com.mengya.dianping.recognizer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AnalysisFunctionRecognizer
 * @Description 分词函数识别器
 * @Author xiongwei.wu
 * @Date 2021/4/28 10:23
 **/
@Component
@Slf4j
public class AnalysisFunctionRecognizer {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * key: 品类服务Id value: 对应品类服务相关的词汇
     */
    private final Map<Integer, List<String>> categoryWorkMap = new HashMap<>();

    @PostConstruct
    public void init() {
        //美食
        categoryWorkMap.put(1, new ArrayList<>());
        //酒店
        categoryWorkMap.put(2, new ArrayList<>());


        categoryWorkMap.get(1).add("吃饭");
        categoryWorkMap.get(1).add("下午茶");

        categoryWorkMap.get(2).add("睡觉");
        categoryWorkMap.get(2).add("休息");
        categoryWorkMap.get(2).add("住宿");
    }

    /**
     * 匹配解析后的token是否在词库中拥有，拥有就返回key
     *
     * @param token token关键字
     * @return 品类id
     */
    private Integer getCategoryIdByToken(String token) {
        for (Integer key : categoryWorkMap.keySet()) {
            List<String> tokenList = categoryWorkMap.get(key);
            if (tokenList.contains(token)) {
                return key;
            }
        }
        return null;
    }

    /**
     * 将用户输入的关键词，去es分词解析
     *
     * @param keyword 关键字
     * @return Map<String, Object>
     * @throws IOException io
     */
    public Map<String, Object> analyzeCategoryKeyword(String keyword) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();

        //请求分词器api
        Request request = new Request("GET", "/shop/_analyze");
        //构建请求对象
        JSONObject jsonRequestObj = new JSONObject();
        jsonRequestObj.put("field", "name");
        jsonRequestObj.put("text", keyword);
        //将json转换为str
        String requestStr = jsonRequestObj.toJSONString();

        log.info("analyze requestStr:{}", requestStr);

        request.setJsonEntity(requestStr);
        Response response = restHighLevelClient.getLowLevelClient().performRequest(request);
        //获取响应对象
        String responseStr = EntityUtils.toString(response.getEntity());

        log.info("analyze responseStr:{}", responseStr);

        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        JSONArray tokens = jsonObject.getJSONArray("tokens");
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.getJSONObject(i).getString("token");
            Integer categoryId = getCategoryIdByToken(token);
            if (categoryId != null) {
                resultMap.put(token, categoryId);
            }
        }

        return resultMap;
    }
}
