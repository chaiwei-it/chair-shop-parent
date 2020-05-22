package com.mood.entity.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mood.entity.base.PagerResponse;
import com.mood.model.response.RestfulResponse;
import lombok.Data;

import java.util.List;

/**
 * 应用模块
 * @author fenglu
 * @time 2018-06-08 16:08
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class UserPagerResponse extends PagerResponse {

    private List<UserDetailsResponse> list;

    private Long total;
}
