package com.zjut.dubbo.provider.common.utils;

import com.zjut.dubbo.provider.common.constants.Constants;
import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

public class ThreadMdcUtil {

    public static void setTraceIdIfAbsent() {
        if (MDC.get(Constants.LOG_TRACE_ID) == null) {
            MDC.put(Constants.LOG_TRACE_ID, getTraceId());
        }
    }

    public static void setTraceId() {
        MDC.put(Constants.LOG_TRACE_ID, getTraceId());
    }

    public static void setTraceId(String traceId) {
        MDC.put(Constants.LOG_TRACE_ID, traceId);
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

    public static class ThreadPoolExecutorWrap extends ThreadPoolExecutor {
        public ThreadPoolExecutorWrap(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }
        public ThreadPoolExecutorWrap(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }
        public ThreadPoolExecutorWrap(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }
        public ThreadPoolExecutorWrap(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

    }

    private static String getTraceId() {
        return UUID.randomUUID().toString();
    }
}
