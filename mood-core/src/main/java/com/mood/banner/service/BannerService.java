package com.mood.banner.service;

import com.mood.base.service.BaseService;
import com.mood.entity.banner.Banner;
import com.mood.entity.banner.request.*;
import com.mood.entity.banner.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface BannerService extends BaseService<Banner> {

    public BannerInsertResponse insert(BannerInsertRequest request);

    public BannerUpdateResponse update(BannerUpdateRequest request);

    public BannerDeleteResponse delete(BannerDeleteRequest request);

    public BannerSelectResponse select(BannerSelectRequest request);

    public BannerListResponse selectList(BannerListRequest request);

    public BannerPagerResponse selectPager(BannerPagerRequest request);

}
