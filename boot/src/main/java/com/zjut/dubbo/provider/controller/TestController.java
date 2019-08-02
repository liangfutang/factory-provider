package com.zjut.dubbo.provider.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zjut.dubbo.provider.common.utils.ThreadMdcUtil;
import com.zjut.dubbo.provider.common.response.RestResponse;
import com.zjut.dubbo.provider.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private ThreadPoolExecutor threadPool = new ThreadMdcUtil.ThreadPoolExecutorWrap(10, 30, 0,TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("测试线程--%d").build());
    private CountDownLatch countDownLatch = new CountDownLatch(10);

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public RestResponse test() {
        logger.info("日志测试");
        System.out.println("进入到测试方法");
        int i = 1/0;
        return new RestResponse(testService.getTest());
    }

    @GetMapping("/testThreadPool")
    public RestResponse testThreadPool() {
//        CountDownLatch countDownLatch = new CountDownLatch(10);
        logger.info("线程池外面");
        threadPool.submit(() -> {
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                logger.error("线程等待异常:" + e);
            }
            logger.info("线程池内部，当前线程:{},执行", Thread.currentThread().getName());
        });
        return new RestResponse("success");
    }
}
