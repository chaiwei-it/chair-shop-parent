package com.mood.entity.admin.response;

import com.mood.entity.user.response.UserInfo;
import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-06 17:06
 */
@Data
public class LoginResponse extends RestfulResponse {



    @ApiModelProperty(value = "token", example = "1", required = true)
    private String token;

    @ApiModelProperty(value = "userInfo", example = "1", required = true)
    private AdminInfo adminInfo;
}
