package com.mood.entity.typeSpec;

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
@Table(name="rebate_type_spec")
public class TypeSpec extends BaseEntity {

    @Id
    private String id;

    private String typeId;

    private String specId;


}