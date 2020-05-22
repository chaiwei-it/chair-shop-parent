package com.mood.specValue.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.specValue.SpecValue;
import com.mood.specValue.dao.SpecValueDao;
import com.mood.specValue.dao.mapper.SpecValueMapper;
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
public class SpecValueDaoImpl implements SpecValueDao {

   @Autowired
    private SpecValueMapper specValueMapper;

    @Override
    public int insert(SpecValue specValue) {
        return specValueMapper.insert(specValue);
    }

    @Override
    public int update(SpecValue specValue) {
        return specValueMapper.updateByPrimaryKeySelective(specValue);
    }

    @Override
    public int deleteById(String id) {
        return specValueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SpecValue selectById(String id) {
        return specValueMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SpecValue> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<SpecValue> selectAll(SpecValue specValue, String... data) {
        Example example = new Example(SpecValue.class);
        Example.Criteria criteria = example.createCriteria();
        if (specValue != null) {
            //拼接条件
            Object name = specValue.getName();
            if (name != null && !"".equals(name)) {
                criteria.andLike("name", "%" + name.toString() + "%");
            }
            Object specId = specValue.getSpecId();
            if (specId != null && !"".equals(specId)) {
                criteria.andEqualTo("specId", specId.toString());
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
        return specValueMapper.selectByExample(example);
    }

    @Override
    public Pager<SpecValue> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<SpecValue> result = selectAll((SpecValue)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
