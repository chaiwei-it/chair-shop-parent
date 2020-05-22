package com.mood.entity.agent.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class AgentUpdateRequest implements Serializable {


    /**
     * 名称
     */
    @NotNull(message = "请输入ID")
    @NotBlank(message = "请输入ID")
    private String id;

    /**
     * 名称
     */
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

    private Integer rebateStatus;

}