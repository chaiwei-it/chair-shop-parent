package com.mood.entity.user;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-06 15:14
 */
@Data
@Table(name="md_user")
public class User extends BaseEntity {

    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态 0：禁用   1：正常
     */
    private int status;

}