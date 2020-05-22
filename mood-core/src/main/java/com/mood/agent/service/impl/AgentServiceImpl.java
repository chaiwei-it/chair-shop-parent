package com.mood.agent.service.impl;

import com.mood.agent.dao.AgentDao;
import com.mood.agent.service.AgentService;
import com.mood.base.Pager;
import com.mood.entity.agent.Agent;
import com.mood.entity.agent.request.*;
import com.mood.entity.agent.response.*;
import com.mood.entity.rabate.Wxpay;
import com.mood.entity.userRebate.UserRebate;
import com.mood.rebate.service.RebateService;
import com.mood.userRebate.service.UserRebateService;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
import com.mood.wxpay.service.WxpayService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private UserRebateService userRebateService;

    @Autowired
    private RebateService rebateService;

    @Autowired
    private WxpayService wxpayService;

    @Override
    public int insert(Agent agent) {
        agent.setId(IdGen.uuid());
        agent.setCreateTime(System.currentTimeMillis());
        agent.setUpdateTime(System.currentTimeMillis());
        agent.setDelStatus(0);
        return agentDao.insert(agent);
    }

    @Override
    public int update(Agent agent) {
        agent.setUpdateTime(System.currentTimeMillis());
        return agentDao.update(agent);
    }

    @Override
    public int deleteById(String id) {
        return agentDao.deleteById(id);
    }

    @Override
    public Agent selectById(String id) {
        return agentDao.selectById(id);
    }

    @Override
    public List<Agent> selectAll(JSONObject param) {
        return agentDao.selectAll(param);
    }

    @Override
    public Pager<Agent> selectPager(Pager pager){
        return agentDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AgentInsertResponse insert(AgentInsertRequest request){
        Agent agent = OrikaMapper.map(request, Agent.class);
        agent.setId(IdGen.uuid());
        agentDao.insert(agent);
        return OrikaMapper.map(agent, AgentInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AgentUpdateResponse update(AgentUpdateRequest request){
        Agent agent = OrikaMapper.map(request, Agent.class);
        agentDao.update(agent);
        if(agent.getAgentStatus() != null){
            if(agent.getAgentStatus() == 2){
                agent = agentDao.selectById(agent.getId());
                UserRebate userRebate = userRebateService.selectById(agent.getUserId());
                userRebate.setGrade(agent.getGrade());
                userRebateService.update(userRebate);
                if(request.getRebateStatus() != null && request.getRebateStatus() == 1){
                    rebateService.agentRebate(agent.getId());
                }
                agent.setPayStatus(1);
                agentDao.update(agent);
            }
        }
        return OrikaMapper.map(agent, AgentUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AgentDeleteResponse delete(AgentDeleteRequest request){
        Agent agent = agentDao.selectById(request.getId());
        if(agent != null){
            agentDao.deleteById(agent.getId());
//            UserRebate userRebate = userRebateService.selectByUserId(agent.getUserId());
//            userRebate.setGrade(1);
//            userRebateService.update(userRebate);
        }
        return new AgentDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AgentSelectResponse select(AgentSelectRequest request){
        Agent agent = agentDao.selectById(request.getId());
        return OrikaMapper.map(agent, AgentSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AgentListResponse selectList(AgentListRequest request){
        Agent agent = OrikaMapper.map(request, Agent.class);
        List<Agent> agentList = agentDao.selectAll(agent);
        List<AgentDetailsResponse> detailsList = OrikaMapper.mapList(agentList, AgentDetailsResponse.class);
        AgentListResponse response = new AgentListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AgentPagerResponse selectPager(AgentPagerRequest request){
        Agent agent = OrikaMapper.map(request, Agent.class);
        Pager<Agent> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(agent);
        pager = agentDao.selectPager(pager);
        List<AgentDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), AgentDetailsResponse.class);
        AgentPagerResponse response = OrikaMapper.map(pager, AgentPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

    /**
     * 支付完成
     * @param wxpay
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void payFinish(Wxpay wxpay){
        //修改支付状态
        wxpayService.update(wxpay);
        Agent agent = selectById(wxpay.getOrderId());
        UserRebate userRebate = userRebateService.selectById(wxpay.getUserId());
        if(userRebate == null || agent == null){
            System.out.println("当前订单不存在：时间-" + new Date() + "内容" + wxpay);
        }
        //修改代理状态
        agent.setPayStatus(1);
        agent.setAgentStatus(2);
        update(agent);
        //修改用户身份
        userRebate.setGrade(agent.getGrade());
        userRebateService.update(userRebate);
        rebateService.agentRebate(wxpay.getOrderId());
    }

}
