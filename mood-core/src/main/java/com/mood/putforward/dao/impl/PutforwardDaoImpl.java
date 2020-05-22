package com.mood.putforward.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.putforward.Putforward;
import com.mood.entity.spec.Spec;
import com.mood.putforward.dao.PutforwardDao;
import com.mood.putforward.dao.mapper.PutforwardMapper;
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
public class PutforwardDaoImpl implements PutforwardDao {

   @Autowired
    private PutforwardMapper putforwardMapper;

    @Override
    public int insert(Putforward putforward) {
        return putforwardMapper.insert(putforward);
    }

    @Override
    public int update(Putforward putforward) {
        return putforwardMapper.updateByPrimaryKeySelective(putforward);
    }

    @Override
    public int deleteById(String id) {
        return putforwardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Putforward selectById(String id) {
        return putforwardMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Putforward> selectAll(JSONObject param) {
        Example example = new Example(Putforward.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object userId = param.get("userId");
            if (userId != null) {
                criteria.andEqualTo("userId", userId.toString());
            }
        }
        example.setOrderByClause("create_time desc");
        return putforwardMapper.selectByExample(example);
    }

    @Override
    public List<Putforward> selectAll(Putforward putforward, String... data) {
        Example example = new Example(Putforward.class);
        Example.Criteria criteria = example.createCriteria();
        if (putforward != null) {
            //拼接条件
            Integer status = putforward.getStatus();
            if (status != null && status != 0) {
                criteria.andEqualTo("status", status);
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
        return putforwardMapper.selectByExample(example);
    }

    @Override
    public Pager<Putforward> selectPager(Pager pager){
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Putforward> result = selectAll((Putforward)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
