package com.mood.orderGoods.service.impl;

import com.mood.base.Pager;
import com.mood.entity.orderGoods.OrderGoods;
import com.mood.orderGoods.dao.OrderGoodsDao;
import com.mood.orderGoods.service.OrderGoodsService;
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
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Autowired
    private OrderGoodsDao orderGoodsDao;


    @Override
    public int insert(OrderGoods orderGoods) {
        orderGoods.setId(IdGen.uuid());
        orderGoods.setCreateTime(System.currentTimeMillis());
        return orderGoodsDao.insert(orderGoods);
    }

    @Override
    public int update(OrderGoods orderGoods) {
        return orderGoodsDao.update(orderGoods);
    }

    @Override
    public int deleteById(String id) {
        return orderGoodsDao.deleteById(id);
    }

    @Override
    public OrderGoods selectById(String id) {
        return orderGoodsDao.selectById(id);
    }

    @Override
    public List<OrderGoods> selectAll(JSONObject param) {
        return orderGoodsDao.selectAll(param);
    }

    @Override
    public Pager<OrderGoods> selectPager(Pager pager){
        return orderGoodsDao.selectPager(pager);
    }

    @Override
    public List<OrderGoods> selectByOrderId(String orderId){
        JSONObject param = new JSONObject();
        param.put("orderId", orderId);
        List<OrderGoods> orderGoodsList = selectAll(param);
        return orderGoodsList;
    }

}
