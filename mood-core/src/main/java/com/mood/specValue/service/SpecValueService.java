package com.mood.specValue.service;

import com.mood.entity.specValue.SpecValue;
import com.mood.entity.specValue.request.*;
import com.mood.entity.specValue.response.*;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface SpecValueService {

    public SpecValueInsertResponse insert(SpecValueInsertRequest request);

    public SpecValueUpdateResponse update(SpecValueUpdateRequest request);

    public SpecValueDeleteResponse delete(SpecValueDeleteRequest request);

    public SpecValueSelectResponse select(SpecValueSelectRequest request);

    public SpecValueListResponse selectList(SpecValueListRequest request);

    public SpecValuePagerResponse selectPager(SpecValuePagerRequest request);

    public List<SpecValueDetailsResponse> selectBySpecId(String specId);
}
