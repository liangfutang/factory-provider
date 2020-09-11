package com.zjut.factory.provider.config.filters;

import com.zjut.factory.provider.common.constants.Constants;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;
import java.util.UUID;

public class TraceIdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put(Constants.LOG_TRACE_ID, UUID.randomUUID().toString());
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }

    @Override
    public void destroy() {

    }
}
