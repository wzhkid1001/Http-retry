package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Retry;
import com.example.demo.mapper.RetryMapper;
import com.example.demo.service.HttpService;
import com.example.demo.uitls.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class HttpServiceImpl implements HttpService {

    @Autowired
    private RetryMapper retryMapper;

    public static int retryTimes = 0;

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    public void httpRequest(Retry retry) throws Exception {
        HashMap headerMap = JSON.parseObject(retry.getHeader(), HashMap.class);
        HashMap paramMap = JSON.parseObject(retry.getParam(), HashMap.class);
        boolean success = HttpClientUtils.doGet(retry.getUrl(), headerMap, paramMap).getCode() == 201;
        if (!success) {
            retryTimes++;
            log.info("失败{}次", retryTimes);
            throw new RuntimeException();
        }
    }

    @Override
    @Recover
    public void recover(Retry retry) {
        retry.getUrl();
        retryMapper.insertSelective(retry);
        log.info("重试失败，结束");
    }
}
