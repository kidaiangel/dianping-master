package com.mengya.dianping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.mengya.dianping")
@MapperScan("com.mengya.dianping.dal")
@EnableAspectJAutoProxy(proxyTargetClass = true) //使springboot容器可以去解析Aop的配置
//@EnableScheduling //启用定时器
public class DianPingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DianPingApplication.class, args);
    }

}
