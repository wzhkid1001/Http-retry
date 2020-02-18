package com.example.demo.entity;

import java.util.Date;

public class Retry {
    private Integer id;

    private Integer methodName;

    private String url;

    private String param;

    private String header;

    private Integer amount;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Retry(Integer id, Integer methodName, String url, String param, String header, Integer amount, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.methodName = methodName;
        this.url = url;
        this.param = param;
        this.header = header;
        this.amount = amount;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Retry() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMethodName() {
        return methodName;
    }

    public void setMethodName(Integer methodName) {
        this.methodName = methodName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}