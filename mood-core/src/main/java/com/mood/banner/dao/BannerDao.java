package com.mood.banner.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.banner.Banner;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface BannerDao extends BaseDao<Banner> {

    public List<Banner> selectAll(Banner t, String... data);

}
