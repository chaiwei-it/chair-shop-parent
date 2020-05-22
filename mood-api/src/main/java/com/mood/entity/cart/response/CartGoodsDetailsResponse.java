package com.mood.entity.cart.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.entity.goods.response.GoodsCartDetailsResponse;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class CartGoodsDetailsResponse {

    /**
     * 购物车id
     */
    @Id
    private String id;

    /**
     * 会员id
     */
    private String userId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 购买商品数量
     */
    private Integer goodsNum;

    /**
     * 商品图片
     */
    private String goodsImage;

    /**
     * 商品规格id
     */
    private String specId;

    /**
     * 商品规格内容
     */
    private String specInfo;

    /**
     * 商品规格内容
     */
    private GoodsCartDetailsResponse goods;


}