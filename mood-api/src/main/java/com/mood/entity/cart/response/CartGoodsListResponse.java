package com.mood.entity.cart.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class CartGoodsListResponse extends RestfulResponse {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private List<CartGoodsDetailsResponse> list;

    private Integer goodsNum;

    private BigDecimal goodsPrice;


}