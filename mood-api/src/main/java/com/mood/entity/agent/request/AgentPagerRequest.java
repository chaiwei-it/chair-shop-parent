package com.mood.entity.agent.request;

import com.mood.entity.base.Pager;
import lombok.Data;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class AgentPagerRequest extends Pager {

    private String username;

    private Integer agentStatus;

    private Integer payStatus;

}