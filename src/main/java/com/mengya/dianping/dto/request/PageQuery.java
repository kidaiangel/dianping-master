package com.mengya.dianping.dto.request;

import lombok.Data;

/**
 * @ClassName PageQuery
 * @Description 分页基类
 * @Author xiongwei.wu
 * @Date 2021/4/20 16:59
 **/
@Data
public class PageQuery {
    private Integer page = 1;

    private Integer size = 6;
}
