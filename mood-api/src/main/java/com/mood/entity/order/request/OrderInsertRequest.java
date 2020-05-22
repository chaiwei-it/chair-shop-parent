package com.mood.entity.order.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class OrderInsertRequest implements Serializable {

    /**
     * 名称
     */
    @NotNull(message = "请选择商品")
    @NotBlank(message = "请选择商品")
    private String cartIds;

    @NotNull(message = "请选择收件地址")
    @NotBlank(message = "请选择收件地址")
    private String addressId;

    private String buyerId;
}