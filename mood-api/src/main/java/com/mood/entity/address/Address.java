package com.mood.entity.address;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * rebate_address
 * @author 
 */
@Data
@Table(name="rebate_address")
public class Address extends BaseEntity {

    @Id
    private String id;

    /**
     * 姓名
     */
    private String userId;

    /**
     * 姓名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 市
     */
    private String cityName;

    /**
     * 区
     */
    private String areaName;

    /**
     * 省
     */
    private Integer provinceId;

    /**
     * 市
     */
    private Integer cityId;

    /**
     * 区
     */
    private Integer areaId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 是否默认
     */
    private Integer defaultStatus;

}