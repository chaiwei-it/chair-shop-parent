package com.mood.entity.agent;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * rebate_agent
 * @author 
 */
@Data
@Table(name="rebate_agent")
public class Agent extends BaseEntity {

    @Id
    private String id;

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

    private String reason;

    private Integer payStatus;

}