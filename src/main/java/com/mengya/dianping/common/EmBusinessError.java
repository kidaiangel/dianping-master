package com.mengya.dianping.common;

/**
 * @ClassName EmBusinessError
 * @Description 错误码描述
 * @Author xiongwei.wu
 * @Date 2021/4/19 17:12
 **/
public enum EmBusinessError {
    //通用的错误类型10000开头
    NO_OBJECT_FOUND(10001, "请求对象不存在"),
    UNKNOWN_ERROR(10002, "未知错误"),
    NO_HANDLER_FOUND(10003, "找不到执行的路径操作"),
    BIND_EXCEPTION_ERROR(10004, "请求参数错误"),
    PARAMETER_VALIDATION_ERROR(10005, "请求参数校验失败"),

    //用户服务相关的错误类型20000开头
    REGISTER_DUP_FAIL(20001, "用户已存在"),
    LOGIN_FAIL(20002, "手机号或密码错误"),
    SESSION_INVALID(20003, "session无效"),
    SESSION_HAVE_EXPIRED(20004, "session已过期"),

    //admin相关错误
    ADMIN_SHOULD_LOGIN(30001, "管理员需要先登录"),
    ADMIN_NAME_PASSWORD_NOT_NULL(30002, "admin用户名或密码不能为空"),
    ADMIN_NAME_PASSWORD_LOGIN_FAIL(30003, "admin用户名或密码错误"),

    //品类相关错误
    CATEGORY_NAME_DUPLICATED(40001,"品类名已存在"),
    ;

    private Integer errCode;

    private String errMsg;

    EmBusinessError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
