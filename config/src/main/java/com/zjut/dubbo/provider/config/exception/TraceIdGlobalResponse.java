package com.zjut.dubbo.provider.config.exception;

import com.zjut.dubbo.provider.common.constants.Constants;
import com.zjut.dubbo.provider.common.response.RestResponse;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 拦截所有的response，并向其中添加traceID
 */
@ControllerAdvice
public class TraceIdGlobalResponse implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof RestResponse) {
            RestResponse restResponse = (RestResponse)o;
            restResponse.setSessionId(MDC.get(Constants.LOG_TRACE_ID));
        }
        return o;
    }
}
