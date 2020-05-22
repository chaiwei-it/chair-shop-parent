package com.mood.goodsSpec.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.goodsSpec.GoodsSpec;
import com.mood.goodsSpec.dao.GoodsSpecDao;
import com.mood.goodsSpec.dao.mapper.GoodsSpecMapper;
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
public class GoodsSpecDaoImpl implements GoodsSpecDao {

   @Autowired
    private GoodsSpecMapper goodsSpecMapper;

    @Override
    public int insert(GoodsSpec goodsSpec) {
        return goodsSpecMapper.insert(goodsSpec);
    }

    @Override
    public int update(GoodsSpec goodsSpec) {
        return goodsSpecMapper.updateByPrimaryKeySelective(goodsSpec);
    }

    @Override
    public int deleteById(String id) {
        return goodsSpecMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GoodsSpec selectById(String id) {
        return goodsSpecMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsSpec> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<GoodsSpec> selectAll(GoodsSpec goodsSpec, String... data) {
        Example example = new Example(GoodsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        if (goodsSpec != null) {
            //拼接条件
            Object goodsId = goodsSpec.getGoodsId();
            if (goodsId != null && !"".equals(goodsId)) {
                criteria.andEqualTo("goodsId", goodsId);
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
        return goodsSpecMapper.selectByExample(example);
    }

    @Override
    public Pager<GoodsSpec> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<GoodsSpec> result = selectAll((GoodsSpec)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
