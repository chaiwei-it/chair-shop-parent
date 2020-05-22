package com.mood.agent.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.agent.Agent;
import com.mood.agent.dao.AgentDao;
import com.mood.agent.dao.mapper.AgentMapper;
import com.mood.entity.userRebate.UserRebate;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Repository
public class AgentDaoImpl implements AgentDao {

   @Autowired
    private AgentMapper agentMapper;

    @Override
    public int insert(Agent agent) {
        return agentMapper.insert(agent);
    }

    @Override
    public int update(Agent agent) {
        return agentMapper.updateByPrimaryKeySelective(agent);
    }

    @Override
    public int deleteById(String id) {
        return agentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Agent selectById(String id) {
        return agentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Agent> selectAll(JSONObject param) {
        Example example = new Example(Agent.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object userId = param.get("userId");
            if (userId != null) {
                criteria.andEqualTo("userId", userId.toString());
            }
            Object grade = param.get("grade");
            if (grade != null) {
                criteria.andEqualTo("grade", grade);
            }
            Object provinceId = param.get("provinceId");
            if (provinceId != null) {
                criteria.andEqualTo("provinceId", provinceId);
            }
            Object cityId = param.get("cityId");
            if (cityId != null) {
                criteria.andEqualTo("cityId", cityId);
            }
            Object areaId = param.get("areaId");
            if (areaId != null) {
                criteria.andEqualTo("areaId", areaId);
            }
        }
        example.setOrderByClause("id asc");
        return agentMapper.selectByExample(example);
    }

    @Override
    public List<Agent> selectAll(Agent agent, String... data) {
        Example example = new Example(Agent.class);
        Example.Criteria criteria = example.createCriteria();
        if (agent != null) {
            //拼接条件
            Object username = agent.getUsername();
            if (username != null && !"".equals(username)) {
                criteria.andLike("username", "%" + username.toString() + "%");
            }
            Integer agentStatus = agent.getAgentStatus();
            if (agentStatus != null && agentStatus != 0) {
                criteria.andEqualTo("agentStatus", agentStatus);
            }
            Integer payStatus = agent.getPayStatus();
            if (payStatus != null) {
                criteria.andEqualTo("payStatus", payStatus);
            }
        }
        String orderBy = "";
        if(data.length > 0){
            orderBy = data[0];
        }else{
            orderBy = "id asc";
        }
        if(!"".equals(orderBy)){
            example.setOrderByClause(orderBy);
        }
        return agentMapper.selectByExample(example);
    }

    @Override
    public Pager<Agent> selectPager(Pager pager){
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Agent> result = selectAll((Agent)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }



}
