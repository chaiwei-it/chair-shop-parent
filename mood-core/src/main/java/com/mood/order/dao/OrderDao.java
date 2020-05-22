package com.mood.order.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.agent.Agent;
import com.mood.entity.order.Order;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface OrderDao extends BaseDao<Order> {

    public List<Order> selectAll(Order order, String... data);

}
