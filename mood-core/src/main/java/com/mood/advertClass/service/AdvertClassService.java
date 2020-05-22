package com.mood.advertClass.service;

import com.mood.base.service.BaseService;
import com.mood.entity.advert.AdvertClass;
import com.mood.entity.advert.request.*;
import com.mood.entity.advert.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AdvertClassService extends BaseService<AdvertClass> {

    public AdvertClassInsertResponse insert(AdvertClassInsertRequest request);

    public AdvertClassUpdateResponse update(AdvertClassUpdateRequest request);

    public void delete(AdvertClassDeleteRequest request);

    public AdvertClassSelectResponse select(AdvertClassSelectRequest request);

    public AdvertClassListResponse selectList(AdvertClassListRequest request);

    public AdvertClassPagerResponse selectPager(AdvertClassPagerRequest request);

}
