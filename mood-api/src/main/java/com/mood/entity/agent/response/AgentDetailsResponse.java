package com.mood.entity.agent.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.entity.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class AgentDetailsResponse extends BaseEntity {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private String id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "测试名称", example = "1", required = true)
    private String userId;

    private String username;

    private String mobile;

    private String cardNum;

    private BigDecimal price;

    private String province;

    private String city;

    private String area;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String details;

    private Integer grade;

    private Integer agentStatus;

}