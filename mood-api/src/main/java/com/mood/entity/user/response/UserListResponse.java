package com.mood.entity.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-08 15:47
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserListResponse extends RestfulResponse {

    @ApiModelProperty(value = "用户列表",required = true)
    private List<UserDetailsResponse> list;
}
