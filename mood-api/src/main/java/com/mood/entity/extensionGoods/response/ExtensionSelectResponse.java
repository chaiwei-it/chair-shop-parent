package com.mood.entity.extensionGoods.response;

import com.mood.model.response.RestfulResponse;
import lombok.Data;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class ExtensionSelectResponse extends RestfulResponse {

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