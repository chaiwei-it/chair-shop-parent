package com.mood.entity.goods.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class GoodsUpdateRequest implements Serializable {


    /**
     * 名称
     */
    @NotNull(message = "请输入ID")
    @NotBlank(message = "请输入ID")
    private String id;

    /**
     * 类型id
     */
    @NotNull(message = "请先选择商品类型")
    @NotBlank(message = "请先选择商品类型")
    private String typeId;

    /**
     * 商品名称
     */
    @NotNull(message = "请输入昵称")
    @NotBlank(message = "请输入昵称")
    private String name;

    /**
     * 副标题
     */
    @NotNull(message = "请输入副标题")
    @NotBlank(message = "请输入副标题")
    private String subtitle;

    /**
     * 成本
     */
    @NotNull(message = "请输入成本")
    private BigDecimal costPrice;

    /**
     * 原价
     */
    @NotNull(message = "请输入原价")
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    @NotNull(message = "请输入现价")
    private BigDecimal nowPrice;

    /**
     * 缩略图
     */
    @NotNull(message = "请上传缩略图")
    @NotBlank(message = "请上传缩略图")
    private String thumbnailImage;

    /**
     * 首页展示图
     */
    @NotNull(message = "请上传首页展示图")
    @NotBlank(message = "请上传首页展示图")
    private String oblongImage;

    /**
     * 展示图
     */
    private String exhibitionImage;

    /**
     * 详情图
     */
    @NotNull(message = "请上传详情图")
    @NotBlank(message = "请上传详情图")
    private String detailImage;

    @NotNull(message = "请上传详情图")
    private Boolean goodsSpecStatus;

    private String specList;

    private List<AddGoodsSpecRequest> goodsSpecList;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 普通会员返利
     */
    @NotNull(message = "请输入普通会员返利金额")
    private BigDecimal memberPrice;

    /**
     * VIP会员返利
     */
    @NotNull(message = "请输入VIP会员返利金额")
    private BigDecimal vipPrice;

    /**
     * 总代理返利
     */
    @NotNull(message = "请输入总代理返利金额")
    private BigDecimal agentPrice;

    /**
     * 区域代理返利
     */
    @NotNull(message = "请输入区域代理返利金额")
    private BigDecimal areaPrice;

    /**
     * 市级代理返利
     */
    @NotNull(message = "请输入市级代理返利金额")
    private BigDecimal cityPrice;

    /**
     * 省级代理返利
     */
    @NotNull(message = "请输入省级代理返利金额")
    private BigDecimal provincePrice;

}