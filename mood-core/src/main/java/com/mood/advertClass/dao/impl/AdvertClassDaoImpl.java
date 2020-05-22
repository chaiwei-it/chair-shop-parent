package com.mood.advertClass.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.advertClass.dao.AdvertClassDao;
import com.mood.advertClass.dao.mapper.AdvertClassMapper;
import com.mood.base.Pager;
import com.mood.entity.advert.AdvertClass;
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
public class AdvertClassDaoImpl implements AdvertClassDao {

   @Autowired
    private AdvertClassMapper advertClassMapper;

    @Override
    public int insert(AdvertClass advert) {
        return advertClassMapper.insert(advert);
    }

    @Override
    public int update(AdvertClass advert) {
        return advertClassMapper.updateByPrimaryKeySelective(advert);
    }

    @Override
    public int deleteById(String id) {
        return advertClassMapper.deleteByPrimaryKey(id);
    }

    @Override
    public AdvertClass selectById(String id) {
        return advertClassMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AdvertClass> selectAll(JSONObject param) {
        Example example = new Example(AdvertClass.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object advertType = param.get("advertType");
            if (advertType != null) {
                criteria.andEqualTo("advertType", advertType);
            }
        }
        example.setOrderByClause("order_by asc");
        return advertClassMapper.selectByExample(example);
    }

    @Override
    public List<AdvertClass> selectAll(AdvertClass advert, String... data) {
        Example example = new Example(AdvertClass.class);
        Example.Criteria criteria = example.createCriteria();
        if (advert != null) {
            //拼接条件
            String keywords = advert.getKeywords();
            if (keywords != null && "".equals(keywords)) {
                criteria.andEqualTo("keywords", keywords);
            }
        }
        String orderBy = "";
        if(data.length > 0){
            orderBy = data[0];
        }else{
            orderBy = "order_by asc";
        }
        if(!"".equals(orderBy)){
            example.setOrderByClause(orderBy);
        }
        return advertClassMapper.selectByExample(example);
    }

    @Override
    public Pager<AdvertClass> selectPager(Pager pager){
        Example example = new Example(AdvertClass.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<AdvertClass> result = advertClassMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
