package com.mood.test.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.test.Test;
import com.mood.test.dao.TestDao;
import com.mood.test.dao.mapper.TestMapper;
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
public class TestDaoImpl implements TestDao {

   @Autowired
    private TestMapper testMapper;

    @Override
    public int insert(Test test) {
        return testMapper.insert(test);
    }

    @Override
    public int update(Test test) {
        return testMapper.updateByPrimaryKeySelective(test);
    }

    @Override
    public int deleteById(String id) {
        return testMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Test selectById(String id) {
        return testMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Test> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<Test> selectAll(Test test, String... data) {
        Example example = new Example(Test.class);
        Example.Criteria criteria = example.createCriteria();
        if (test != null) {
            //拼接条件
            Object name = test.getName();
            if (name != null) {
                criteria.andLike("name", "%" + name.toString() + "%");
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
        return testMapper.selectByExample(example);
    }

    @Override
    public Pager<Test> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Test> result = selectAll((Test)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
