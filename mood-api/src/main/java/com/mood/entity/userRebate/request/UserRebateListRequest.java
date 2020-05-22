package com.mood.entity.userRebate.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class UserRebateListRequest implements Serializable {

    /**
     * 名称
     */
    private String username;

    private String parentId;

    private Integer num;


}