package com.zjut.dubbo.provider.config.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private TraceIdFilter traceIdFilter;

    public FilterRegistrationBean setTraceIdFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(traceIdFilter);
        registration.addUrlPatterns("/*");
        registration.setName("traceIdFilter");
        registration.setOrder(1);
        return registration;
    }
}
