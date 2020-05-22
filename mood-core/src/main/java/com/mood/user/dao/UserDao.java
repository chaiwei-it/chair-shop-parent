package com.mood.user.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.user.User;

import java.util.List;

/**
 * 模块
 *
 * @author fenglu
 * @time 2018-06-06 17:40
 */
public interface UserDao extends BaseDao<User> {

    public List<User> selectAll(User t, String... data);

    public User selectOne(User user);

}
