package com.mood.orderGoods.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.orderGoods.OrderGoods;
import com.mood.orderGoods.dao.OrderGoodsDao;
import com.mood.orderGoods.dao.mapper.OrderGoodsMapper;
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
public class OrderGoodsDaoImpl implements OrderGoodsDao {

   @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Override
    public int insert(OrderGoods orderGoods) {
        return orderGoodsMapper.insert(orderGoods);
    }

    @Override
    public int update(OrderGoods orderGoods) {
        return orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
    }

    @Override
    public int deleteById(String id) {
        return orderGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderGoods selectById(String id) {
        return orderGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderGoods> selectAll(JSONObject param) {
        Example example = new Example(OrderGoods.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object orderId = param.get("orderId");
            if (orderId != null) {
                criteria.andEqualTo("orderId", orderId.toString());
            }
        }
        example.setOrderByClause("id desc");
        return orderGoodsMapper.selectByExample(example);
    }

    @Override
    public Pager<OrderGoods> selectPager(Pager pager){
        Example example = new Example(OrderGoods.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<OrderGoods> result = orderGoodsMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
