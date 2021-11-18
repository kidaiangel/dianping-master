package com.mengya.dianping.common;

import com.mengya.dianping.constant.CommonConstant;

/**
 * @ClassName CommonRes
 * @Description 通用返回类
 * @Author xiongwei.wu
 * @Date 2021/4/19 16:59
 **/
public class CommonRes {
    /**
     * 表明读经请求的返回处理结果，"Success"或"Fail"
     */
    private String status;

    /**
     * 若status=success时，表明对应的返回的json类数据
     * 若status=fail时，则data内将使用通用的错误码对应的格式
     */
    private Object data;

    /**
     * 定义一个通用的创建返回对象的方法
     *
     * @param result 结果集
     * @return CommonRes
     */
    public static CommonRes create(Object result) {
        return CommonRes.create(result, CommonConstant.MESSAGE_STATUS_SUCCESS);
    }

    public static CommonRes create(Object result, String status) {
        CommonRes commonRes = new CommonRes();
        commonRes.setData(result);
        commonRes.setStatus(status);

        return commonRes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
