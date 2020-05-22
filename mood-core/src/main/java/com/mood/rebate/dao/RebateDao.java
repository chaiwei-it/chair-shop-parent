package com.mood.rebate.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.rabate.Rebate;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface RebateDao extends BaseDao<Rebate> {

    public List<Rebate> getRebateList(String rebateNum);
}
