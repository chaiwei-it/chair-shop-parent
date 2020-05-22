package com.mood.cart.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.cart.Cart;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface CartDao extends BaseDao<Cart> {

    public List<Cart> selectAll(Cart t, String... data);

}
