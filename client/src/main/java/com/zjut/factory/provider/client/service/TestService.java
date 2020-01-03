package com.zjut.factory.provider.client.service;

import com.zjut.factory.provider.client.dto.TestDubboValidationDto;

public interface TestService {
    String getTest();

    @interface Register{}
    String testValidation(TestDubboValidationDto testDubboValidation);
}
