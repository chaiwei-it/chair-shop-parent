package com.mood.orderAddress.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.orderAddress.OrderAddress;
import com.mood.orderAddress.dao.OrderAddressDao;
import com.mood.orderAddress.dao.mapper.OrderAddressMapper;
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
public class OrderAddressDaoImpl implements OrderAddressDao {

   @Autowired
    private OrderAddressMapper orderAddressMapper;

    @Override
    public int insert(OrderAddress orderAddress) {
        return orderAddressMapper.insert(orderAddress);
    }

    @Override
    public int update(OrderAddress orderAddress) {
        return orderAddressMapper.updateByPrimaryKeySelective(orderAddress);
    }

    @Override
    public int deleteById(String id) {
        return orderAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderAddress selectById(String id) {
        return orderAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderAddress> selectAll(JSONObject param) {
        Example example = new Example(OrderAddress.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object orderId = param.get("orderId");
            if (orderId != null) {
                criteria.andEqualTo("orderId", orderId.toString());
            }
        }
        example.setOrderByClause("id desc");
        return orderAddressMapper.selectByExample(example);
    }

    @Override
    public Pager<OrderAddress> selectPager(Pager pager){
        Example example = new Example(OrderAddress.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<OrderAddress> result = orderAddressMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
