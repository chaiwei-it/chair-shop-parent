package com.mood.userOpen.service;

import com.mood.base.service.BaseService;
import com.mood.entity.rabate.UserOpen;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface UserOpenService extends BaseService<UserOpen> {

    public void getOpenId(String userId, String unionId, String openId, Integer appId);

    UserOpen selectByUserId(String userId);
}
