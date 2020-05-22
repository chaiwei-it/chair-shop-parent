package com.mood.entity.userRebate.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserRebateUpdateResponse extends RestfulResponse {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private String id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "测试名称", example = "1", required = true)
    private String name;


}