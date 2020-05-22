package com.mood.entity.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.entity.base.Pager;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-08 16:11
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserPagerRequest extends Pager {

    @NotNull(message = "请输入用户名")
    @NotBlank(message = "请输入用户名")
    private String username;

    private String email;

    private String moible;

    private int status;
}
