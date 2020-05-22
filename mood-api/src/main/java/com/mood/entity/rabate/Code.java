package com.mood.entity.rabate;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * rebate_code
 * @author 
 */
@Data
@Table(name="rebate_code")
public class Code extends BaseEntity {

    @Id
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 二维码地址
     */
    private String codeUrl;

    /**
     * 应用类型
     */
    private Integer appType;


}