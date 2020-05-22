package com.mood.recommendClass.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.recommend.RecommendClass;


import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface RecommendClassDao extends BaseDao<RecommendClass> {

    public List<RecommendClass> selectAll(RecommendClass t, String... data);

}
