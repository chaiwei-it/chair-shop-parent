package com.mood.specValue.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.specValue.SpecValue;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface SpecValueDao extends BaseDao<SpecValue> {

    public List<SpecValue> selectAll(SpecValue t, String... data);

}
