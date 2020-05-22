package com.mood.recommendClass.service;

import com.mood.base.service.BaseService;
import com.mood.entity.recommend.RecommendClass;
import com.mood.entity.recommend.request.*;
import com.mood.entity.recommend.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface RecommendClassService extends BaseService<RecommendClass> {

    public RecommendClassInsertResponse insert(RecommendClassInsertRequest request);

    public RecommendClassUpdateResponse update(RecommendClassUpdateRequest request);

    public void delete(RecommendClassDeleteRequest request);

    public RecommendClassSelectResponse select(RecommendClassSelectRequest request);

    public RecommendClassListResponse selectList(RecommendClassListRequest request);

    public RecommendClassPagerResponse selectPager(RecommendClassPagerRequest request);

}
