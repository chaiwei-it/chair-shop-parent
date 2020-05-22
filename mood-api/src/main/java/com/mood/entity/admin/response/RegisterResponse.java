package com.mood.entity.admin.response;

import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-06 17:06
 */
@Data
public class RegisterResponse extends RestfulResponse {



    @ApiModelProperty(value = "用户名称",required = true)
    private String username;

}
