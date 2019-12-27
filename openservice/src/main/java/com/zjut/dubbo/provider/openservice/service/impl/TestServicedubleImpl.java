package com.zjut.dubbo.provider.openservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjut.dubbo.provider.client.dto.TestDubboValidationDto;
import com.zjut.dubbo.provider.client.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 次类为TestServiceImpl的副本，用来测试TestService的不同实现类，通过group在消费者端调用时的区别
 */
@Service(version = "1.0.0", validation="true", group = "rose")
public class TestServicedubleImpl  implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestServicedubleImpl.class);

    @Override
    public String getTest() {
        logger.info("副本中的简单方法调用成功");
        return "副本方法模拟调用成功";
    }

    @Override
    public String testValidation(TestDubboValidationDto testDubboValidation) {
        logger.info("副本中模拟入参校验方法调用成功");
        return "副本中模拟入参校验方法调用成功";
    }
}
