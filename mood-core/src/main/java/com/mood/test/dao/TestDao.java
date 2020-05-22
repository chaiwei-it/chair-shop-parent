package com.mood.test.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.test.Test;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface TestDao extends BaseDao<Test> {

    public List<Test> selectAll(Test t, String... data);

}
