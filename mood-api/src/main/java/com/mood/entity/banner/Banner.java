package com.mood.entity.banner;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * rebate_banner
 * @author 
 */
@Data
@Table(name="rebate_banner")
public class Banner extends BaseEntity {
    @Id
    private String id;

    /**
     * 展示图片
     */
    private String imageUrl;

    /**
     * 关联类型
     */
    private Integer relation;

    /**
     * 关联内容
     */
    private String content;

    /**
     * 说明
     */
    private String notes;

    /**
     * 位置
     */
    private String keywords;

    /**
     * 排序
     */
    private Integer sortNum;

}