package com.mood.spec.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.spec.Spec;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface SpecDao extends BaseDao<Spec> {

    public List<Spec> selectAll(Spec t, String... data);

}
