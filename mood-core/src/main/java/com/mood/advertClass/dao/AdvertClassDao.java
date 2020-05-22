package com.mood.advertClass.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.advert.Advert;
import com.mood.entity.advert.AdvertClass;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AdvertClassDao extends BaseDao<AdvertClass> {

    public List<AdvertClass> selectAll(AdvertClass t, String... data);

}
