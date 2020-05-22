package com.mood.entity.advert;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * rebate_advert
 * @author 
 */
@Data
@Table(name="rebate_advert")
public class Advert extends BaseEntity {
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