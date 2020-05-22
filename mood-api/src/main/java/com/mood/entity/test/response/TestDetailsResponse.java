package com.mood.entity.test.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class TestDetailsResponse  {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private String id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "测试名称", example = "1", required = true)
    private String name;


}