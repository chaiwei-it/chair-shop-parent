package com.mood.putforward.service;

import com.mood.base.service.BaseService;
import com.mood.entity.putforward.Putforward;
import com.mood.entity.putforward.request.*;
import com.mood.entity.putforward.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface PutforwardService extends BaseService<Putforward> {

    public PutforwardInsertResponse insert(PutforwardInsertRequest request);

    public PutforwardUpdateResponse update(PutforwardUpdateRequest request);

    public PutforwardDeleteResponse delete(PutforwardDeleteRequest request);

    public PutforwardSelectResponse select(PutforwardSelectRequest request);

    public PutforwardListResponse selectList(PutforwardListRequest request);

    public PutforwardPagerResponse selectPager(PutforwardPagerRequest request);
}
