package com.example.demo.controller;

import com.example.demo.entity.Retry;
import com.example.demo.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private HttpService httpService;

    @GetMapping("/test")
    public void test() throws Exception {
        Retry retry = new Retry();
        retry.setUrl("http://localhost:8080/hello/get");
        retry.setMethodName(1);
        httpService.httpRequest(retry);
    }

}
