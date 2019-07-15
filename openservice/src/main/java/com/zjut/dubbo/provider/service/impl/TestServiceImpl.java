package com.zjut.dubbo.provider.service.impl;

import com.zjut.dubbo.provider.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String getTest() {
        return "dubbo测试成功";
    }
}
