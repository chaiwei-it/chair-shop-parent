package com.mood.entity.user.response;

import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-06 17:06
 */
@Data
public class UserInsertResponse extends RestfulResponse {

    @ApiModelProperty(value = "用户id",example = "1",required = true)
    private String id;

    @ApiModelProperty(value = "用户名称",required = true)
    private String username;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "手机号",required = true)
    private String mobile;

    @ApiModelProperty(value = "状态",required = true)
    private int status;
}
