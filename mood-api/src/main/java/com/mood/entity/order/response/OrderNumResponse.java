package com.mood.entity.order.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.model.response.RestfulResponse;
import lombok.Data;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class OrderNumResponse extends RestfulResponse {
    /**
     * 待支付
     */
    private Integer notPay;

    /**
     * 待发货
     */
    private Integer notCollect;

    /**
     * 到收货
     */
    private Integer notDeliver;

    /**
     * 已完成
     */
    private Integer orderFinish;

}