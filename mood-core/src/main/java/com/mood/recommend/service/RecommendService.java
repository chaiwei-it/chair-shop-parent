package com.mood.recommend.service;

import com.mood.base.service.BaseService;
import com.mood.entity.recommend.request.*;
import com.mood.entity.recommend.response.*;
import com.mood.entity.recommend.Recommend;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface RecommendService extends BaseService<Recommend> {

    public RecommendInsertResponse insert(RecommendInsertRequest request);

    public RecommendUpdateResponse update(RecommendUpdateRequest request);

    public void delete(RecommendDeleteRequest request);

    public RecommendSelectResponse select(RecommendSelectRequest request);

    public RecommendListResponse selectList(RecommendListRequest request);

    public RecommendPagerResponse selectPager(RecommendPagerRequest request);

}
