package com.mood.entity.goods.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.entity.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ClassGoodsDetailsResponse extends BaseEntity {

    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 缩略图
     */
    private String thumbnailImage;

    /**
     * 首页展示图
     */
    private String oblongImage;

    /**
     * 展示图
     */
    private String exhibitionImage;

    /**
     * 详情图
     */
    private String detailImage;

    /**
     * 详情
     */
    private String detail;

    /**
     * 分类id
     */
    private String classId;

    /**
     * 分类名称
     */
    private String className;

    /**
     * 分类关键字
     */
    private String keywords;

    /**
     * 类型id
     */
    private String typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 成本
     */
    private BigDecimal costPrice;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    private BigDecimal nowPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 是否开卖
     */
    private Integer sellStatus;

    /**
     * 推广码
     */
    private String goodsNum;

    /**
     * 推广码
     */
    private String brand;

    /**
     * 是否自营
     */
    private Integer goodsType;

    /**
     * 状态
     */
    private Integer status;

}