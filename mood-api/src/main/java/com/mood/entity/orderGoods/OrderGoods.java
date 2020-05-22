package com.mood.entity.orderGoods;

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
@Table(name="rebate_order_goods")
public class OrderGoods extends BaseEntity {

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
     * 商品id
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 规格id
     */
    private String specId;

    /**
     * 规格描述
     */
    private String specInfo;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 商品图片
     */
    private String goodsImage;

    /**
     * 店铺ID
     */
    private String storesId;

}