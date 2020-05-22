package com.mood.rebate.service;

import com.mood.base.service.BaseService;
import com.mood.entity.rabate.Rebate;
import com.mood.model.response.RestfulResponse;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface RebateService extends BaseService<Rebate> {

    public List<Rebate> getRebateList(String rebateNum);

    /**
     * 购物返利
     * @param orderId
     * @return
     */
    public Boolean orderRebate(String orderId);

    /**
     * 升级返利
     * @param agentId
     * @return
     */
    public Boolean agentRebate(String agentId);

    public RestfulResponse rebateByAgent(String agentId);

    /**
     * 返利
     * @param
     * @return
     */
    public void rebate(Rebate rebate);



    /**
     * 手动创建返利
     * @param buyId
     * @param rebateId
     * @param goodsId
     * @return
     */
//    public Integer create(String buyId, String rebateId, String orderId);
}
