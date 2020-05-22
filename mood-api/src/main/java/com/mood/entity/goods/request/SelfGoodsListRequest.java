package com.mood.entity.goods.request;

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
public class SelfGoodsListRequest implements Serializable {

    /**
     * 名称
     */
    @NotNull(message = "请输入关键字")
    @NotBlank(message = "请输入关键字")
    private String goodsType;

}