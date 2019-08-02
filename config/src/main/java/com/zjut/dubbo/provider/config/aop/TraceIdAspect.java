//package com.zjut.dubbo.provider.config.aop;
//
//import com.zjut.dubbo.provider.common.response.RestResponse;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//
///**
// * 用于向所有的response中添加traceID
// */
//@Aspect
//@Component
//public class TraceIdAspect {
//
//    @Pointcut("execution(public * com.zjut.dubbo.provider.controller.*.*(..))")
//    public void apiRequest() {}
//
//    @AfterReturning(returning = "returnValue", pointcut = "apiRequest()")
//    public void insertApiError(JoinPoint point, Object returnValue) {
//        if (returnValue instanceof RestResponse) {
//            RestResponse restResponse = (RestResponse)returnValue;
//            restResponse.setSessionId(MDC.get("traceId"));
//        }
//        System.out.println("kazhuan");
//    }
//}
