package com.zjut.dubbo.provider.controller;

import com.zjut.dubbo.provider.response.RestResponse;
import com.zjut.dubbo.provider.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public RestResponse test() {
        logger.info("日志测试");
        System.out.println("进入到测试方法");
        return new RestResponse(testService.getTest());
    }
}
