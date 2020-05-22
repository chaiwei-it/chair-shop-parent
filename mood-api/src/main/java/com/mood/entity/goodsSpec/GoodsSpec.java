package com.mood.entity.goodsSpec;

import com.mood.entity.base.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@Table(name="rebate_goods_spec")
public class GoodsSpec extends BaseEntity {

    @Id
    private String id;

    /**
     * 名称
     */
    private String goodsId;

    private String specIds;

    private String specNames;

    /**
     * 成本
     */
    @NotNull(message = "请输入成本")
    @NotBlank(message = "请输入成本")
    private BigDecimal costPrice;

    /**
     * 原价
     */
    @NotNull(message = "请输入原价")
    @NotBlank(message = "请输入原价")
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    @NotNull(message = "请输入现价")
    @NotBlank(message = "请输入现价")
    private BigDecimal nowPrice;

    private Integer stock;

    private String image;


}