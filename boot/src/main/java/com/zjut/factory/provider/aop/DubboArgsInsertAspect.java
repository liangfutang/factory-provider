package com.zjut.factory.provider.aop;

import com.zjut.factory.provider.client.dto.TestDubboValidationDto;
import com.zjut.factory.provider.dto.Personer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 测试进来的dubbo接口中添加一个参数
 */
@Component
@Aspect
@Slf4j
public class DubboArgsInsertAspect {
    @Pointcut("execution(public * com.zjut.factory.provider.openservice.service.impl.TestServiceImpl.testValidation(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (ObjectUtils.isEmpty(args)) {
            log.info("本次请求没有入参");
            return;
        }
        for (Object one : args) {
            if (one instanceof TestDubboValidationDto) {
                log.info("是Personer下面的入参，添加一个入参");
                ((TestDubboValidationDto) one).setSex("sex girl");
            }
        }
    }
}
