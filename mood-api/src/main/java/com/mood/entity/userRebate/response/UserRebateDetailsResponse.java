package com.mood.entity.userRebate.response;

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
public class UserRebateDetailsResponse extends BaseEntity {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户ID
     */
    private String mobile;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户编号
     */
    private UserRebateDetailsResponse parent;


    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 顶级id
     */
    private String topId;

    /**
     * 顶级id
     */
    private String topAgentId;

    /**
     * 所属总代
     */
    private Integer level;

    /**
     * 代理级别
     */
    private Integer grade;

    /**
     * 代理余额
     */
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 修改时间
     */
    private String updateDate;


}