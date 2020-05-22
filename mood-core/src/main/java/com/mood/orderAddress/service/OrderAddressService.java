package com.mood.orderAddress.service;

import com.mood.base.service.BaseService;
import com.mood.entity.orderAddress.OrderAddress;
import com.mood.entity.orderGoods.OrderGoods;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface OrderAddressService extends BaseService<OrderAddress> {

    public OrderAddress selectByOrderId(String orderId);

}
