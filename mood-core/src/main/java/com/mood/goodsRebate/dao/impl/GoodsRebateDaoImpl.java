package com.mood.goodsRebate.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.goodsRebate.GoodsRebate;
import com.mood.goodsRebate.dao.GoodsRebateDao;
import com.mood.goodsRebate.dao.mapper.GoodsRebateMapper;
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
public class GoodsRebateDaoImpl implements GoodsRebateDao {

   @Autowired
    private GoodsRebateMapper goodsRebateMapper;

    @Override
    public int insert(GoodsRebate goodsRebate) {
        return goodsRebateMapper.insert(goodsRebate);
    }

    @Override
    public int update(GoodsRebate goodsRebate) {
        return goodsRebateMapper.updateByPrimaryKeySelective(goodsRebate);
    }

    @Override
    public int deleteById(String id) {
        return goodsRebateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GoodsRebate selectById(String id) {
        return goodsRebateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsRebate> selectAll(JSONObject param) {
        Example example = new Example(GoodsRebate.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object goodsId = param.get("goodsId");
            if (goodsId != null) {
                criteria.andEqualTo("goodsId", goodsId.toString());
            }
        }
        example.setOrderByClause("id desc");
        return goodsRebateMapper.selectByExample(example);
    }

    @Override
    public Pager<GoodsRebate> selectPager(Pager pager){
        Example example = new Example(GoodsRebate.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<GoodsRebate> result = goodsRebateMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
