package com.mood.entity.cart.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class CartInsertRequest implements Serializable {

    /**
     * 商品ID
     */
    @NotNull(message = "请输入商品ID")
    @NotBlank(message = "请输入商品ID")
    private String goodsId;

    /**
     * 规格ID
     */
    private String specId;

    /**
     * 商品数量
     */
    @NotNull(message = "请输入商品数量")
    private Integer goodsNum;

    private String userId;


}