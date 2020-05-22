package com.mood.agent.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.agent.Agent;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AgentDao extends BaseDao<Agent> {

    public List<Agent> selectAll(Agent agent, String... data);
}
