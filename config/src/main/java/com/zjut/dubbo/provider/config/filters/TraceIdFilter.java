package com.zjut.dubbo.provider.config.filters;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

//@WebFilter(urlPatterns="/*", value="TraceIdFilter")
@Component
public class TraceIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put("traceId", UUID.randomUUID().toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
