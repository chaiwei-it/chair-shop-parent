package com.mood.goodsSpec.service;

import com.mood.entity.goodsSpec.GoodsSpec;
import com.mood.entity.goodsSpec.request.*;
import com.mood.entity.goodsSpec.response.*;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface GoodsSpecService {

    public Boolean insert(GoodsSpec goodsSpec);

    public GoodsSpecInsertResponse insert(GoodsSpecInsertRequest request);

    public GoodsSpecUpdateResponse update(GoodsSpecUpdateRequest request);

    public void delete(GoodsSpecDeleteRequest request);

    public GoodsSpecSelectResponse select(GoodsSpecSelectRequest request);

    public GoodsSpecListResponse selectList(GoodsSpecListRequest request);

    public GoodsSpecPagerResponse selectPager(GoodsSpecPagerRequest request);

    public List<GoodsSpec> selectByGoodsId(String goodsId);

    public void deleteByGoodsId(String goodsId);
}
