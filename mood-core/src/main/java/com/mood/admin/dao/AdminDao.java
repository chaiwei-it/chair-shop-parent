package com.mood.admin.dao;

import com.mood.base.dao.NewBaseDao;
import com.mood.entity.admin.Admin;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AdminDao extends NewBaseDao<Admin> {

    public Admin selectOne(Admin admin);
}
