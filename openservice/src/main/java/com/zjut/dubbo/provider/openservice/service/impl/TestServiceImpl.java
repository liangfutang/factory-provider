package com.zjut.dubbo.provider.openservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjut.dubbo.provider.client.dto.TestDubboValidationDto;
import com.zjut.dubbo.provider.client.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(version = "1.0.0", validation="true", group = "jack")
public class TestServiceImpl implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public String getTest() {
        return "dubbo测试成功";
    }

    @Override
    public String testValidation(TestDubboValidationDto testDubboValidation) {
        return "测试校验成功";
    }
}
