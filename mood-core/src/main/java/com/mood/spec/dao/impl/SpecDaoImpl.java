package com.mood.spec.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.spec.Spec;
import com.mood.spec.dao.SpecDao;
import com.mood.spec.dao.mapper.SpecMapper;
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
public class SpecDaoImpl implements SpecDao {

   @Autowired
    private SpecMapper specMapper;

    @Override
    public int insert(Spec spec) {
        return specMapper.insert(spec);
    }

    @Override
    public int update(Spec spec) {
        return specMapper.updateByPrimaryKeySelective(spec);
    }

    @Override
    public int deleteById(String id) {
        return specMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Spec selectById(String id) {
        return specMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Spec> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<Spec> selectAll(Spec spec, String... data) {
        Example example = new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if (spec != null) {
            //拼接条件
            Object name = spec.getName();
            if (name != null && !"".equals(name)) {
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
        return specMapper.selectByExample(example);
    }

    @Override
    public Pager<Spec> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Spec> result = selectAll((Spec)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
