package com.mood.entity.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-08 15:50
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserDetailsResponse{

    private String id;

    private String username;

    private String email;

    private String mobile;

    private Integer status;

    private Long createTime;

}
