package com.mood.entity.admin;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@Table(name="rebate_admin")
public class Admin extends BaseEntity {

    @Id
    private String id;

    /**
     * 名称
     */
    private String username;

    /**
     * 名称
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


}