package com.mood.entity.address.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class DefaultAddressRequest implements Serializable {

    /**
     * 名称
     */
    private String userId;


}