package com.mood.goodsSpec.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.goodsSpec.GoodsSpec;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface GoodsSpecDao extends BaseDao<GoodsSpec> {

    public List<GoodsSpec> selectAll(GoodsSpec t, String... data);

}
