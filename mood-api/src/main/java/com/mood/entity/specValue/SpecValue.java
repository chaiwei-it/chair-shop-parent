package com.mood.entity.specValue;

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
@Table(name="rebate_spec_value")
public class SpecValue extends BaseEntity {

    @Id
    private String id;

    private String specId;

    /**
     * 名称
     */
    private String name;

    private String image;

    private Integer showStatus;

    private Integer sortNum;



}