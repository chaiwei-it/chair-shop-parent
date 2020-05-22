package com.mood.goodsRebate.service;

import com.mood.base.service.BaseService;
import com.mood.entity.goodsRebate.GoodsRebate;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface GoodsRebateService extends BaseService<GoodsRebate> {

    public GoodsRebate selectByGoodId(String goodsId);

    public void deleteByGoodsId(String goodsId);
}
