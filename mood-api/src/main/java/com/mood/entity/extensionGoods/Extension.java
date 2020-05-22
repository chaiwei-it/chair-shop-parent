package com.mood.entity.extensionGoods;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@Table(name="rebate_extension_goods")
public class Extension extends BaseEntity {


    @Id
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