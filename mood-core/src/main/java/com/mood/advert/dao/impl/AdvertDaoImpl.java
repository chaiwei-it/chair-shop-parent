package com.mood.advert.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.advert.dao.AdvertDao;
import com.mood.advert.dao.mapper.AdvertMapper;
import com.mood.base.Pager;
import com.mood.entity.advert.Advert;
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
public class AdvertDaoImpl implements AdvertDao {

   @Autowired
    private AdvertMapper advertMapper;

    @Override
    public int insert(Advert advert) {
        return advertMapper.insert(advert);
    }

    @Override
    public int update(Advert advert) {
        return advertMapper.updateByPrimaryKeySelective(advert);
    }

    @Override
    public int deleteById(String id) {
        return advertMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Advert selectById(String id) {
        return advertMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Advert> selectAll(JSONObject param) {
        Example example = new Example(Advert.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object advertType = param.get("advertType");
            if (advertType != null) {
                criteria.andEqualTo("advertType", advertType);
            }
        }
        example.setOrderByClause("order_by asc");
        return advertMapper.selectByExample(example);
    }

    @Override
    public List<Advert> selectAll(Advert advert, String... data) {
        Example example = new Example(Advert.class);
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
        return advertMapper.selectByExample(example);
    }

    @Override
    public Pager<Advert> selectPager(Pager pager){
        Example example = new Example(Advert.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Advert> result = advertMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
