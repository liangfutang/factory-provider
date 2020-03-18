package com.zjut.factory.provider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// TODO 后面想想办法能不能把DubboComponentScan也整合到starter的模块中去
@SpringBootApplication
@DubboComponentScan(basePackages = {"com.zjut.factory.provider.openservice.service.impl"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
