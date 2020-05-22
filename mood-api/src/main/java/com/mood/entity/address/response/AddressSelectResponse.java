package com.mood.entity.address.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class AddressSelectResponse extends RestfulResponse {

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