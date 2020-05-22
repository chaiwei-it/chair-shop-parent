package com.mood.entity.extensionGoods.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ExtensionUpdateRequest implements Serializable {


    private String id;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 排序
     */
    private Integer sortNum;

}