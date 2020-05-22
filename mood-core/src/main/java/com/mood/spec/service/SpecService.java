package com.mood.spec.service;

import com.mood.entity.spec.request.*;
import com.mood.entity.spec.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface SpecService {

    public SpecInsertResponse insert(SpecInsertRequest request);

    public SpecUpdateResponse update(SpecUpdateRequest request);

    public SpecDeleteResponse delete(SpecDeleteRequest request);

    public SpecSelectResponse select(SpecSelectRequest request);

    public SpecListResponse selectList(SpecListRequest request);

    public SpecValueDatailsListResponse selectList(SpecValueDatailsListRequest request);

    public SpecPagerResponse selectPager(SpecPagerRequest request);
}
