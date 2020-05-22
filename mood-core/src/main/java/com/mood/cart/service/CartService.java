package com.mood.cart.service;

import com.mood.entity.cart.request.*;
import com.mood.entity.cart.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface CartService {

    public CartInsertResponse insert(CartInsertRequest request);

    public CartUpdateResponse update(CartUpdateRequest request);

    public CartDeleteResponse delete(CartDeleteRequest request);

    public CartSelectResponse select(CartSelectRequest request);

    public CartListResponse selectList(CartListRequest request);

    public CartPagerResponse selectPager(CartPagerRequest request);

    public CartNumResponse selectCartNum(CartListRequest request);

    public CartGoodsListResponse selectGoodsList(CartGoodsListRequest request);
}
