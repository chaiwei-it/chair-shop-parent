package com.mood.goodsClass.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.goodsClass.GoodsClass;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface GoodsClassDao extends BaseDao<GoodsClass> {

    public List<GoodsClass> selectAll(GoodsClass t, String... data);

}
