package com.mood.goodsType.service;

import com.mood.entity.goodsType.GoodsType;
import com.mood.entity.goodsType.request.*;
import com.mood.entity.goodsType.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface GoodsTypeService {

    public GoodsTypeInsertResponse insert(GoodsTypeInsertRequest request);

    public GoodsTypeUpdateResponse update(GoodsTypeUpdateRequest request);

    public GoodsTypeDeleteResponse delete(GoodsTypeDeleteRequest request);

    public GoodsTypeSelectResponse select(GoodsTypeSelectRequest request);

    public GoodsTypeListResponse selectList(GoodsTypeListRequest request);

    public GoodsTypePagerResponse selectPager(GoodsTypePagerRequest request);

    public GoodsType selectById(String id);
}
