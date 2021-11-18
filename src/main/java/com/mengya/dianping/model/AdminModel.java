package com.mengya.dianping.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName AdminModel
 * @Description admin用户模型，目前取application配置文件值
 * @Author xiongwei.wu
 * @Date 2021/4/20 14:32
 **/
@Component
@Data
public class AdminModel {

    @Value("${admin.email}")
    private String email;

    @Value("${admin.encryptPassword}")
    private String encryptPassword;

}
