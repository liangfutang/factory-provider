package com.zjut.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjut.dubbo.provider.service.TestService;

@Service(version = "1.0.0")
public class TestServiceImpl implements TestService {

    

    @Override
    public String getTest() {
        return "dubbo测试成功";
    }
}
