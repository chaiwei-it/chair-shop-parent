package com.mood.goods.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.goods.Goods;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface GoodsDao extends BaseDao<Goods> {

    public List<Goods> selectAll(Goods t, String... data);

}
