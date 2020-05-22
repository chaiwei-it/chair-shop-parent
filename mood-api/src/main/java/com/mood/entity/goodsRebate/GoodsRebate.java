package com.mood.entity.goodsRebate;

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
@Table(name="rebate_goods_rebate")
public class GoodsRebate {

    @Id
    private String id;

    /**
     * 商品id
     */
    private String goodsId;

    private Integer storeType;

    private BigDecimal memberPrice;

    private BigDecimal vipPrice;

    private BigDecimal agentPrice;

    private BigDecimal areaPrice;

    private BigDecimal cityPrice;

    private BigDecimal provincePrice;
}