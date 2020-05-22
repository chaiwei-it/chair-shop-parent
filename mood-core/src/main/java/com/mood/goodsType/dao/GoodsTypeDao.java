package com.mood.goodsType.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.goodsType.GoodsType;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface GoodsTypeDao extends BaseDao<GoodsType> {

    public List<GoodsType> selectAll(GoodsType t, String... data);

}
