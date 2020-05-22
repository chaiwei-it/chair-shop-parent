package com.mood.wxpay.service;

import com.mood.base.service.BaseService;
import com.mood.entity.rabate.Image;
import com.mood.entity.rabate.Wxpay;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface WxpayService extends BaseService<Wxpay> {

    Wxpay selectByOrderId(String orderId);

    void updateWxpay(Wxpay wxpay);
}
