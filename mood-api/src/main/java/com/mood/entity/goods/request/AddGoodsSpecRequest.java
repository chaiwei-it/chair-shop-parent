package com.mood.entity.goods.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class AddGoodsSpecRequest{

    private String id;

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

    /**
     * 库存
     */
    private Integer stock;


}