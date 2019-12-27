package com.zjut.dubbo.provider.client.service;

import com.zjut.dubbo.provider.client.dto.TestDubboValidationDto;

public interface TestService {
    String getTest();

    @interface Register{}
    String testValidation(TestDubboValidationDto testDubboValidation);
}
