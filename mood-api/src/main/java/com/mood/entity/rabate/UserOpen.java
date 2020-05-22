package com.mood.entity.rabate;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * rebate_user_open
 * @author 
 */
@Data
@Table(name="rebate_user_open")
public class UserOpen extends BaseEntity {

    @Id
    private String id;

    /**
     * 应用ID
     */
    private Integer appId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     *
     */
    private String openId;

    /**
     *
     */
    private String unionId;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 上次登录时间
     */
    private Long lastLoginTime;


}