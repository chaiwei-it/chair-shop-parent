package com.mood.userOpen.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.rabate.UserOpen;
import com.mood.userOpen.dao.UserOpenDao;
import com.mood.userOpen.dao.mapper.UserOpenMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Repository
public class UserOpenDaoImpl implements UserOpenDao {

   @Autowired
    private UserOpenMapper userOpenMapper;

    @Override
    public int insert(UserOpen userOpen) {
        return userOpenMapper.insert(userOpen);
    }

    @Override
    public int update(UserOpen userOpen) {
        return userOpenMapper.updateByPrimaryKeySelective(userOpen);
    }

    @Override
    public int deleteById(String id) {
        return userOpenMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserOpen selectById(String id) {
        return userOpenMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserOpen> selectAll(JSONObject param) {
        Example example = new Example(UserOpen.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object appId = param.get("appId");
            if (appId != null) {
                criteria.andEqualTo("appId", appId);
            }
            Object userId = param.get("userId");
            if (userId != null) {
                criteria.andEqualTo("userId", userId);
            }
            Object unionId = param.get("unionId");
            if (unionId != null) {
                criteria.andEqualTo("unionId", unionId.toString());
            }
            Object openId = param.get("openId");
            if (openId != null) {
                criteria.andEqualTo("openId", openId.toString());
            }
        }
        example.setOrderByClause("create_time desc");
        return userOpenMapper.selectByExample(example);
    }

    @Override
    public Pager<UserOpen> selectPager(Pager pager){
        Example example = new Example(UserOpen.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<UserOpen> result = userOpenMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
