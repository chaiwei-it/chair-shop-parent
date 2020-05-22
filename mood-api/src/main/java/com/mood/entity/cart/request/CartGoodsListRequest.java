package com.mood.entity.cart.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class CartGoodsListRequest implements Serializable {

    private String cartIds;


}