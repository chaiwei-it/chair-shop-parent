package com.mood.recommend.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.recommend.Recommend;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface RecommendDao extends BaseDao<Recommend> {

    public List<Recommend> selectAll(Recommend t, String... data);

}
