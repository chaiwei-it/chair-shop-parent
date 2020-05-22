package com.mood.template.service;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface TemplateService {

    void pay(String orderId);

    void send(String orderId);

    void finish(String orderId);

}
