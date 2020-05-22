package com.mood.order.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.agent.Agent;
import com.mood.entity.order.Order;
import com.mood.order.dao.OrderDao;
import com.mood.order.dao.mapper.OrderMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Repository
public class OrderDaoImpl implements OrderDao {

   @Autowired
    private OrderMapper orderMapper;

    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int update(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int deleteById(String id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Order selectById(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> selectAll(JSONObject param) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object buyerId = param.get("buyerId");
            if (buyerId != null) {
                criteria.andEqualTo("buyerId", buyerId.toString());
            }
        }
        example.setOrderByClause("create_time desc");
        return orderMapper.selectByExample(example);
    }

    @Override
    public List<Order> selectAll(Order order, String... data){
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if (order != null) {
            //拼接条件
            String buyerId = order.getBuyerId();
            if (buyerId != null && !"".equals(buyerId)) {
                criteria.andEqualTo("buyerId", buyerId);
            }
            Integer orderStatus = order.getOrderStatus();
            if (orderStatus != null && orderStatus != 0) {
                criteria.andEqualTo("orderStatus", orderStatus);
            }
        }
        String orderBy = "";
        if(data.length > 0){
            orderBy = data[0];
        }else{
            orderBy = "id asc";
        }
        if(!"".equals(orderBy)){
            example.setOrderByClause(orderBy);
        }
        return orderMapper.selectByExample(example);
    }

    @Override
    public Pager<Order> selectPager(Pager pager){
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Order> result = selectAll((Order)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
