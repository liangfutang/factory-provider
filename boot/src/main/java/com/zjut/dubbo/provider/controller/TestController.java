package com.zjut.dubbo.provider.controller;

import com.zjut.dubbo.provider.response.RestResponse;
import com.zjut.dubbo.provider.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public RestResponse test() {
        System.out.println("进入到测试方法");
        return new RestResponse(testService.getTest());
    }
}
