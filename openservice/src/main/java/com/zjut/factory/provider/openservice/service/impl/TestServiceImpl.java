package com.zjut.factory.provider.openservice.service.impl;

import com.zjut.factory.provider.client.dto.TestDubboValidationDto;
import com.zjut.factory.provider.client.service.TestService;
import org.apache.dubbo.config.annotation.Service;
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
        logger.info("进来的参数:" + testDubboValidation);
        return "测试校验成功";
    }
}
