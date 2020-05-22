package com.mood.entity.putforward.response;

import com.mood.entity.base.PagerResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class PutforwardPagerResponse extends PagerResponse {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private List<PutforwardDetailsResponse> list;

}