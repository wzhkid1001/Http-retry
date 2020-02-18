package com.example.demo.task;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Retry;
import com.example.demo.entity.RetryExample;
import com.example.demo.mapper.RetryMapper;
import com.example.demo.uitls.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.demo.enums.Constants.METHOD_NAME_GET;
import static com.example.demo.enums.Constants.METHOD_NAME_POST;

@Component
@Slf4j
public class HttpTask {

    private final ExecutorService exe = Executors.newFixedThreadPool(3);
    @Autowired
    private RetryMapper retryMapper;


    @Scheduled(fixedDelay = 5 * 60 * 1000L)
    public void retry() {
        // 1.找到重试次数少于3次的记录
        RetryExample example = new RetryExample();
        example.createCriteria().andStatusEqualTo(0).andAmountLessThanOrEqualTo(3);
        List<Retry> retries = retryMapper.selectByExample(example);

        retries.forEach(retry -> {
            exe.execute(() -> {
                HashMap headerMap = JSON.parseObject(retry.getHeader(), HashMap.class);
                HashMap paramMap = JSON.parseObject(retry.getParam(), HashMap.class);
                boolean success = false;
                // 2.请求重试
                switch (retry.getMethodName()) {
                    case METHOD_NAME_GET:
                        try {
                            success = HttpClientUtils.doGet(retry.getUrl(), headerMap, paramMap).getCode() == 200;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case METHOD_NAME_POST:
                        try {
                            success = HttpClientUtils.doPost(retry.getUrl(), headerMap, paramMap).getCode() == 200;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
                // 3.判断请求状态，更新数据库

            });
        });
    }
}
