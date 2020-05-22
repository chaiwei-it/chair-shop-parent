package com.mood.user.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.user.User;
import com.mood.user.dao.UserDao;
import com.mood.user.dao.mapper.UserMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-06 17:40
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteById(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User selectById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<User> selectAll(User user, String... data) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if(user!=null){
            Object username = user.getUsername();
            if(username!=null && !"".equals(username)){
                criteria.andEqualTo("username",username.toString());
            }
        }
        String orderBy = "";
        if(data.length > 0){
            orderBy = data[0];
        }else{
            orderBy = "id asc";
        }
        if(!"".equals(orderBy)){
            example.setOrderByClause(orderBy);
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public Pager<User> selectPager(Pager pager) {
        PageHelper.startPage(pager.getPageIndex(),pager.getPageSize());
        List<User> result = selectAll((User) pager.getObject(),pager.getOrderBy());
        PageInfo<User> pageInfo = new PageInfo<User>(result);
        return pager.buildPager(pageInfo.getPages(),pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public User selectOne(User user){
        return userMapper.selectOne(user);
    }
}
