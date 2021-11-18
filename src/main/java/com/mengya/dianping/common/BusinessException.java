package com.mengya.dianping.common;

/**
 * @ClassName BusinessException
 * @Description 通用异常处理
 * @Author xiongwei.wu
 * @Date 2021/4/19 17:23
 **/
public class BusinessException extends Exception {

    /**
     * 错误返回对象
     */
    private CommonError commonError;

    public BusinessException(EmBusinessError emBusinessError) {
        super();
        this.commonError = new CommonError(emBusinessError);
    }

    public BusinessException(EmBusinessError emBusinessError, String errMsg) {
        super();
        this.commonError = new CommonError(emBusinessError);
        this.commonError.setErrMsg(errMsg);
    }

    public CommonError getCommonError() {
        return commonError;
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }
}
