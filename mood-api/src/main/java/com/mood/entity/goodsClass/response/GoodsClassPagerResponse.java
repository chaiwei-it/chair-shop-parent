package com.mood.entity.goodsClass.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.entity.base.PagerResponse;
import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class GoodsClassPagerResponse extends PagerResponse {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private List<GoodsClassDetailsResponse> list;

}