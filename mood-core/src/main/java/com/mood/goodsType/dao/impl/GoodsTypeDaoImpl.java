package com.mood.goodsType.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.goodsType.GoodsType;
import com.mood.goodsType.dao.GoodsTypeDao;
import com.mood.goodsType.dao.mapper.GoodsTypeMapper;
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
public class GoodsTypeDaoImpl implements GoodsTypeDao {

   @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public int insert(GoodsType goodsType) {
        return goodsTypeMapper.insert(goodsType);
    }

    @Override
    public int update(GoodsType goodsType) {
        return goodsTypeMapper.updateByPrimaryKeySelective(goodsType);
    }

    @Override
    public int deleteById(String id) {
        return goodsTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GoodsType selectById(String id) {
        return goodsTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsType> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<GoodsType> selectAll(GoodsType goodsType, String... data) {
        Example example = new Example(GoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        if (goodsType != null) {
            //拼接条件
            Object name = goodsType.getName();
            if (name != null && !"".equals(name)) {
                criteria.andLike("name", "%" + name.toString() + "%");
            }
            Object classId = goodsType.getClassId();
            if (classId != null && !"".equals(classId)) {
                criteria.andEqualTo("classId", classId.toString());
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
        return goodsTypeMapper.selectByExample(example);
    }

    @Override
    public Pager<GoodsType> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<GoodsType> result = selectAll((GoodsType)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
