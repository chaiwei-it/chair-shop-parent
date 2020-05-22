package com.mood.goods.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.goods.Goods;
import com.mood.goods.dao.GoodsDao;
import com.mood.goods.dao.mapper.GoodsMapper;
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
public class GoodsDaoImpl implements GoodsDao {

   @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int insert(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public int update(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public int deleteById(String id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Goods selectById(String id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Goods> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<Goods> selectAll(Goods goods, String... data) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        if (goods != null) {
            //拼接条件
            Object name = goods.getName();
            if (name != null && !"".equals(name)) {
                criteria.andLike("name", "%" + name.toString() + "%");
            }
            Object classId = goods.getClassId();
            if (classId != null && !"".equals(classId)) {
                criteria.andEqualTo("classId", classId.toString());
            }
            Object keywords = goods.getKeywords();
            if (keywords != null && !"".equals(keywords)) {
                criteria.andEqualTo("keywords", keywords.toString());
            }
            Object typeId = goods.getTypeId();
            if (typeId != null && !"".equals(typeId)) {
                criteria.andEqualTo("typeId", typeId.toString());
            }
            Integer goodsType = goods.getGoodsType();
            if (goodsType != null) {
                criteria.andEqualTo("goodsType", goodsType);
            }
            Integer status = goods.getStatus();
            if (status != null) {
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
        return goodsMapper.selectByExample(example);
    }

    @Override
    public Pager<Goods> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Goods> result = selectAll((Goods)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
