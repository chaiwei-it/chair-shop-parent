package com.mood.entity.orderAddress;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 订单商品(订单项)
 *
 * @author Liuk
 */
@Data
@Table(name="rebate_order_address")
public class OrderAddress extends BaseEntity {

    /**
     * 订单商品表索引id
     */
    @Id
    private String id;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 省级ID
     */
    private String provinceId;

    /**
     * 市级ID
     */
    private String cityId;

    /**
     * 区级ID
     */
    private String areaId;

    /**
     * 省级名称
     */
    private String provinceName;

    /**
     * 市级名称
     */
    private String cityName;

    /**
     * 区级名称
     */
    private String areaName;

    /**
     * 商品价格
     */
    private String address;

    /**
     * 电话
     */
    private String mobile;

}