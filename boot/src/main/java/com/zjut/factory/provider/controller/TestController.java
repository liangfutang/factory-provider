package com.zjut.factory.provider.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zjut.factory.provider.common.response.RestResponse;
import com.zjut.factory.provider.common.utils.ThreadMdcUtil;
import com.zjut.factory.provider.client.service.TestService;
import com.zjut.factory.provider.dto.Personer;
import com.zjut.factory.provider.openservice.service.impl.TestServiceImpl;
import com.zjut.factory.provider.service.RoleBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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

    @Resource(type = TestServiceImpl.class)
    private TestService testService;

    @Autowired
    private RoleBaseService roleBaseService;

    @GetMapping("/roles")
    public RestResponse getRole() {
        roleBaseService.getRole();
        return new RestResponse("success");
    }

    @RequestMapping("/test")
    public RestResponse test() {
        logger.info("日志测试");
        System.out.println("进入到测试方法");
        int i = 1/0;
        return new RestResponse(testService.getTest());
    }

    private static int num = 0;
    @GetMapping("/testThreadPool")
    public RestResponse testThreadPool() {
        logger.info("线程池外面,第{}次被调用:", num);
        threadPool.submit(() -> {
            countDownLatch.countDown();
            Integer integer = new Integer(num);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                logger.error("线程等待异常:" + e);
            }
            logger.info("线程池内部，当前线程:{},执行。第{}次被调用", Thread.currentThread().getName(), integer);
        });
        num++;
        return new RestResponse("success");
    }

    /**
     * 用来测试aop,PersonerArgChangeAspect的入参能否插入新的参数
     * @param personer
     * @return
     */
    @PostMapping("/aspect/insert/test")
    public RestResponse testPutArgAspect(@RequestBody Personer personer) {
        logger.info("传进来的入参:" + personer);
        return new RestResponse("success");
    }
}
