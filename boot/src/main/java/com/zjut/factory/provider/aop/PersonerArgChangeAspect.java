package com.zjut.factory.provider.aop;

import com.zjut.factory.provider.dto.Personer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

/**
 * 测试http接口的入参中更新参数成功
 */
@Component
@Aspect
@Slf4j
public class PersonerArgChangeAspect {

    @Pointcut("execution(public * com.zjut.factory.provider.controller.TestController.testPutArgAspect(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (ObjectUtils.isEmpty(args)) {
            log.info("本次请求没有入参");
            return;
        }
        for (Object one : args) {
            if (one instanceof Personer) {
                log.info("是Personer下面的入参，添加一个入参");
                ((Personer) one).setCity("安徽芜湖大老板");
            }
        }

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Around:" + Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        log.info("AroundAfter:" + result);
        return result;
    }

}
