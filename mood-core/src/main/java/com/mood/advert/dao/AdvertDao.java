package com.mood.advert.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.advert.Advert;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AdvertDao extends BaseDao<Advert> {

    public List<Advert> selectAll(Advert t, String... data);

}
