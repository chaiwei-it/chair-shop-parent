package com.mood.entity.rabate;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * rebate_image
 * @author 
 */
@Data
@Table(name="rebate_image")
public class Image extends BaseEntity {

    @Id
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 图片路径
     */
    private String imageUrl;


}