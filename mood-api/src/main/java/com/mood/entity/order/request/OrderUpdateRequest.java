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
public class OrderUpdateRequest implements Serializable {


    /**
     * 名称
     */
    @NotNull(message = "请输入ID")
    @NotBlank(message = "请输入ID")
    private String id;

    /**
     * 收件人
     */
    @NotNull(message = "请输入收件人")
    @NotBlank(message = "请输入收件人")
    private String username;

    /**
     * 手机号
     */
    @NotNull(message = "请输入手机号")
    @NotBlank(message = "请输入手机号")
    private String mobile;

    /**
     * 收件地址
     */
    @NotNull(message = "请输入收件地址")
    @NotBlank(message = "请输入收件地址")
    private String address;

    /**
     * 快递单号
     */
//    @NotNull(message = "请输入快递单号")
//    @NotBlank(message = "请输入快递单号")
    private String shippingCode;

}