package com.mood.orderGoods.service;

import com.mood.base.service.BaseService;
import com.mood.entity.orderGoods.OrderGoods;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface OrderGoodsService extends BaseService<OrderGoods> {

    public List<OrderGoods> selectByOrderId(String orderId);
}
