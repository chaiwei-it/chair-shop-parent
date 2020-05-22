package com.mood.orderAddress.service.impl;

import com.mood.base.Pager;
import com.mood.entity.orderAddress.OrderAddress;
import com.mood.entity.orderGoods.OrderGoods;
import com.mood.orderAddress.dao.OrderAddressDao;
import com.mood.orderAddress.service.OrderAddressService;
import com.mood.utils.IdGen;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class OrderAddressServiceImpl implements OrderAddressService {

    @Autowired
    private OrderAddressDao orderAddressDao;


    @Override
    public int insert(OrderAddress orderAddress) {
        orderAddress.setId(IdGen.uuid());
        orderAddress.setCreateTime(System.currentTimeMillis());
        orderAddress.setUpdateTime(System.currentTimeMillis());
        orderAddress.setDelStatus(0);
        return orderAddressDao.insert(orderAddress);
    }

    @Override
    public int update(OrderAddress orderAddress) {
        orderAddress.setUpdateTime(System.currentTimeMillis());
        return orderAddressDao.update(orderAddress);
    }

    @Override
    public int deleteById(String id) {
        return orderAddressDao.deleteById(id);
    }

    @Override
    public OrderAddress selectById(String id) {
        return orderAddressDao.selectById(id);
    }

    @Override
    public List<OrderAddress> selectAll(JSONObject param) {
        return orderAddressDao.selectAll(param);
    }

    @Override
    public Pager<OrderAddress> selectPager(Pager pager){
        return orderAddressDao.selectPager(pager);
    }

    @Override
    public OrderAddress selectByOrderId(String orderId){
        JSONObject param = new JSONObject();
        param.put("orderId", orderId);
        List<OrderAddress> orderGoodsList = selectAll(param);
        if(orderGoodsList.size() > 0){
            return orderGoodsList.get(0);
        }
        return null;
    }

}
