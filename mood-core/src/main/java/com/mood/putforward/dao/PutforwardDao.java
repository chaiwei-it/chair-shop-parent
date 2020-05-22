package com.mood.putforward.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.putforward.Putforward;
import com.mood.entity.spec.Spec;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface PutforwardDao extends BaseDao<Putforward> {

    public List<Putforward> selectAll(Putforward t, String... data);

}
