package com.mood.goods.service;

import com.mood.entity.goods.request.*;
import com.mood.entity.goods.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface GoodsService {

    public GoodsInsertResponse insert(GoodsInsertRequest request);

    public GoodsUpdateResponse update(GoodsUpdateRequest request);

    public GoodsDeleteResponse delete(GoodsDeleteRequest request);

    public GoodsSelectResponse select(GoodsSelectRequest request);

    public GoodsListResponse selectList(GoodsListRequest request);

    public GoodsPagerResponse selectPager(GoodsPagerRequest request);

    /**
     * 获取首页推广商品
     * @param request
     * @return
     */
    public IndexGoodsListResponse selectIndexList(IndexGoodsListRequest request);

    /**
     * 获取分类商品
     * @param request
     * @return
     */
    public ClassGoodsListResponse selectClassGoodsList(ClassGoodsListRequest request);

    /**
     * 获取自营商品
     * @param request
     * @return
     */
    public SelfGoodsListResponse selectSelfGoodsList(SelfGoodsListRequest request);

    /**
     * 修改商品状态
     * @param request
     * @return
     */
    GoodsUpdateStatusResponse updateStatus(GoodsUpdateStatusRequest request);
}
