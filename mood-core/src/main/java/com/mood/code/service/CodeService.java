package com.mood.code.service;

import com.mood.base.service.BaseService;
import com.mood.entity.rabate.Code;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface CodeService extends BaseService<Code> {

    public Code selectByUserId(String memberId);
}
