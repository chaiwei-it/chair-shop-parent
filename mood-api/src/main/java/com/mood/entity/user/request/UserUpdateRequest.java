package com.mood.entity.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-07 11:49
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserUpdateRequest {

    @NotNull(message = "请输入ID")
    @NotBlank(message = "请输入ID")
    private String id;

    @NotBlank(message = "请输入用户名")
    @NotNull(message = "请输入用户名")
    private String username;

    private String password;

    @NotNull(message = "请输入邮箱")
    @NotBlank(message = "请输入邮箱")
    private String email;

    @NotBlank(message = "请输入手机号")
    @NotNull(message = "请输入手机号")
    private String moible;

}
