package com.example.demo.service;

import com.example.demo.entity.Retry;

public interface HttpService {

    void httpRequest(Retry retry) throws Exception;

    void recover(Retry retry);
}
