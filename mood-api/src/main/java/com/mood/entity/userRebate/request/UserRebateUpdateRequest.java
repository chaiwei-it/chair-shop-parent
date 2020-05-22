package com.mood.entity.userRebate.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserRebateUpdateRequest implements Serializable {


    /**
     * 名称
     */
    @NotNull(message = "请输入ID")
    @NotBlank(message = "请输入ID")
    private String id;

    /**
     * 名称
     */
    @NotNull(message = "请输入昵称")
    @NotBlank(message = "请输入昵称")
    private String username;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户编号
     */
    @NotNull(message = "请输入用户ID")
    @NotBlank(message = "请输入用户ID")
    private String userNum;

    /**
     * 用户编号
     */
    @NotNull(message = "请输入上级ID")
    @NotBlank(message = "请输入上级ID")
    private String parentNum;


    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 代理余额
     */
    @NotNull(message = "余额不能为空")
    private BigDecimal balance;


}