package com.mood.entity.order.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.model.response.RestfulResponse;
import lombok.Data;

import javax.persistence.Id;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class OrderAddressResponse extends RestfulResponse {
    /**
     * 订单商品表索引id
     */
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