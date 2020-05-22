package com.mood.entity.goods.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.entity.goods.request.AddGoodsSpecRequest;
import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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
public class GoodsSelectResponse extends RestfulResponse {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private String id;

    private String typeId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "测试名称", example = "1", required = true)
    private String name;

    /**
     * 副标题
     */
    private String subtitle;

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

    private Boolean goodsSpecStatus;

    private String specList;

    private List<AddGoodsSpecRequest> goodsSpecList;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 商品状态
     */
    private Integer status;

    /**
     * 普通会员返利
     */
    private BigDecimal memberPrice;

    /**
     * VIP会员返利
     */
    private BigDecimal vipPrice;

    /**
     * 总代理返利
     */
    private BigDecimal agentPrice;

    /**
     * 区域代理返利
     */
    private BigDecimal provincePrice;

    /**
     * 市级代理返利
     */
    private BigDecimal cityPrice;

    /**
     * 省级代理返利
     */
    private BigDecimal areaPrice;

}