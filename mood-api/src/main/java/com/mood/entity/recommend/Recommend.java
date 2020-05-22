package com.mood.entity.recommend;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * rebate_recommend
 * @author 
 */
@Data
@Table(name="rebate_recommend")
public class Recommend extends BaseEntity {
    @Id
    private String id;

    /**
     *
     */
    private String imageUrl;

    /**
     *
     */
    private String toUrl;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String keywords;

    /**
     *
     */
    private Integer orderBy;

}