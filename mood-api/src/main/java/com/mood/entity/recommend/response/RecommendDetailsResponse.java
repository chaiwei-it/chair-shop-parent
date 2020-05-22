package com.mood.entity.recommend.response;

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
public class RecommendDetailsResponse {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private String id;

    /**
     *
     */
    private String imageUrl;

    /**
     *
     */
    private String toUrl;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String keywords;

    /**
     *
     */
    private Integer orderBy;


}