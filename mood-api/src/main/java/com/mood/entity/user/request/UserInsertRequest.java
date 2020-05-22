package com.mood.entity.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-06 15:16
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"},ignoreUnknown = true)
public class UserInsertRequest implements Serializable {

    @NotBlank(message = "请输入用户名")
    @NotNull(message = "请输入用户名")
    private String username;


    @NotBlank(message = "请输入密码")
    @NotNull(message = "请输入密码")
    private String password;

    @NotBlank(message = "请输入邮箱")
    @NotNull(message = "请输入邮箱")
    private String email;

    @NotBlank(message = "请输入手机号码")
    @NotNull(message = "请输入手机号码")
    private String mobile;

    @NotNull(message = "请输入状态")
    private int status;

}
