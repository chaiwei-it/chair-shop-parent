package com.mood.entity.admin.request;

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
public class RegisterRequest implements Serializable {

    @NotBlank(message = "请输入密码")
    @NotNull(message = "请输入密码")
    private String password;

    @NotBlank(message = "请输入用户名")
    @NotNull(message = "请输入用户名")
    private String username;

    /**
     * 用户头像
     */
    private String headImage;

}
