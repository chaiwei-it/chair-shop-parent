package com.mood.entity.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-08 15:23
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserSelectResponse extends RestfulResponse {

    @ApiModelProperty(value = "用户ID",example = "1",required = true)
    private String id;

    @ApiModelProperty(value = "用户名称",required = true)
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "手机号码",required = true)
    private String mobile;

    @ApiModelProperty(value = "状态",required = true)
    private int status;
}
