package com.mood.userRebate.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.agent.Agent;
import com.mood.entity.userRebate.UserRebate;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface UserRebateDao extends BaseDao<UserRebate> {

    public List<UserRebate> selectAll(UserRebate userRebate, String... data);

}
