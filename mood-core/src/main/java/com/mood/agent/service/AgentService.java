package com.mood.agent.service;

import com.mood.base.service.BaseService;
import com.mood.entity.agent.Agent;
import com.mood.entity.agent.request.*;
import com.mood.entity.agent.response.*;
import com.mood.entity.rabate.Wxpay;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AgentService extends BaseService<Agent> {
    
    public AgentInsertResponse insert(AgentInsertRequest request);

    public AgentUpdateResponse update(AgentUpdateRequest request);

    public AgentDeleteResponse delete(AgentDeleteRequest request);

    public AgentSelectResponse select(AgentSelectRequest request);

    public AgentListResponse selectList(AgentListRequest request);

    public AgentPagerResponse selectPager(AgentPagerRequest request);

    /**
     * 支付完成
     * @param wxpay
     */
    void payFinish(Wxpay wxpay);
}
