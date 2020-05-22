package com.mood.advert.service;

import com.mood.base.service.BaseService;
import com.mood.entity.advert.Advert;
import com.mood.entity.advert.request.*;
import com.mood.entity.advert.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AdvertService extends BaseService<Advert> {

    public AdvertInsertResponse insert(AdvertInsertRequest request);

    public AdvertUpdateResponse update(AdvertUpdateRequest request);

    public void delete(AdvertDeleteRequest request);

    public AdvertSelectResponse select(AdvertSelectRequest request);

    public AdvertListResponse selectList(AdvertListRequest request);

    public AdvertPagerResponse selectPager(AdvertPagerRequest request);

}
