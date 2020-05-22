package com.mood.entity.putforward.response;

import com.mood.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class PutforwardInsertResponse extends RestfulResponse {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private String id;


}