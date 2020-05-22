package com.mood.recommend.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.recommend.Recommend;
import com.mood.recommend.dao.RecommendDao;
import com.mood.recommend.dao.mapper.RecommendMapper;
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
public class RecommendDaoImpl implements RecommendDao {

   @Autowired
    private RecommendMapper bannerMapper;

    @Override
    public int insert(Recommend banner) {
        return bannerMapper.insert(banner);
    }

    @Override
    public int update(Recommend banner) {
        return bannerMapper.updateByPrimaryKeySelective(banner);
    }

    @Override
    public int deleteById(String id) {
        return bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Recommend selectById(String id) {
        return bannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Recommend> selectAll(JSONObject param) {
        Example example = new Example(Recommend.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object bannerType = param.get("bannerType");
            if (bannerType != null) {
                criteria.andEqualTo("bannerType", bannerType);
            }
        }
        example.setOrderByClause("order_by asc");
        return bannerMapper.selectByExample(example);
    }

    @Override
    public List<Recommend> selectAll(Recommend banner, String... data) {
        Example example = new Example(Recommend.class);
        Example.Criteria criteria = example.createCriteria();
        if (banner != null) {
            //拼接条件
            String keywords = banner.getKeywords();
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
        return bannerMapper.selectByExample(example);
    }

    @Override
    public Pager<Recommend> selectPager(Pager pager){
        Example example = new Example(Recommend.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Recommend> result = bannerMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
