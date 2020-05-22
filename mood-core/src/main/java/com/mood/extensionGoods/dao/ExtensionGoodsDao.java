package com.mood.extensionGoods.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.extensionGoods.Extension;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface ExtensionGoodsDao extends BaseDao<Extension> {

    public List<Extension> selectAll(Extension t, String... data);

}
