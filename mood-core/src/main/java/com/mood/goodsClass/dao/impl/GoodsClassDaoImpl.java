package com.mood.goodsClass.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.goodsClass.GoodsClass;
import com.mood.goodsClass.dao.GoodsClassDao;
import com.mood.goodsClass.dao.mapper.GoodsClassMapper;
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
public class GoodsClassDaoImpl implements GoodsClassDao {

   @Autowired
    private GoodsClassMapper goodsClassMapper;

    @Override
    public int insert(GoodsClass goodsClass) {
        return goodsClassMapper.insert(goodsClass);
    }

    @Override
    public int update(GoodsClass goodsClass) {
        return goodsClassMapper.updateByPrimaryKeySelective(goodsClass);
    }

    @Override
    public int deleteById(String id) {
        return goodsClassMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GoodsClass selectById(String id) {
        return goodsClassMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsClass> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<GoodsClass> selectAll(GoodsClass goodsClass, String... data) {
        Example example = new Example(GoodsClass.class);
        Example.Criteria criteria = example.createCriteria();
        if (goodsClass != null) {
            //拼接条件
            String name = goodsClass.getName();
            if (name != null && !"".equals(name)) {
                criteria.andLike("name", "%" + name + "%");
            }
            Integer showStatus = goodsClass.getShowStatus();
            if (showStatus != null) {
                criteria.andEqualTo("showStatus", showStatus);
            }
        }
        String orderBy = "";
        if(data.length > 0){
            orderBy = data[0];
        }else{
            orderBy = "sort_num asc";
        }
        if(!"".equals(orderBy)){
            example.setOrderByClause(orderBy);
        }
        return goodsClassMapper.selectByExample(example);
    }

    @Override
    public Pager<GoodsClass> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<GoodsClass> result = selectAll((GoodsClass)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
