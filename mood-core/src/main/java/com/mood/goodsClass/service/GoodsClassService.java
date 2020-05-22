package com.mood.goodsClass.service;

import com.mood.entity.goodsClass.GoodsClass;
import com.mood.entity.goodsClass.request.*;
import com.mood.entity.goodsClass.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface GoodsClassService {

    public GoodsClass selectById(String id);

    public GoodsClassInsertResponse insert(GoodsClassInsertRequest request);

    public GoodsClassUpdateResponse update(GoodsClassUpdateRequest request);

    public GoodsClassDeleteResponse delete(GoodsClassDeleteRequest request);

    public GoodsClassSelectResponse select(GoodsClassSelectRequest request);

    public GoodsClassListResponse selectList(GoodsClassListRequest request);

    public GoodsClassPagerResponse selectPager(GoodsClassPagerRequest request);

}
