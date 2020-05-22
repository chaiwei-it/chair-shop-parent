package com.mood.extensionGoods.service;

import com.mood.base.service.BaseService;
import com.mood.entity.extensionGoods.Extension;
import com.mood.entity.extensionGoods.request.*;
import com.mood.entity.extensionGoods.response.*;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface ExtensionGoodsService extends BaseService<Extension> {

    public ExtensionInsertResponse insert(ExtensionInsertRequest request);

    public ExtensionUpdateResponse update(ExtensionUpdateRequest request);

    public ExtensionDeleteResponse delete(ExtensionDeleteRequest request);

    public ExtensionSelectResponse select(ExtensionSelectRequest request);

    public ExtensionListResponse selectList(ExtensionListRequest request);

    public ExtensionPagerResponse selectPager(ExtensionPagerRequest request);

    /**
     * 通过关键字获取关联商品
     * @param keywords
     * @return
     */
    public List<Extension> selectByKeywords(String keywords);
}
