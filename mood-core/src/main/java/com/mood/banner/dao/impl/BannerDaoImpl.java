package com.mood.banner.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.banner.dao.BannerDao;
import com.mood.banner.dao.mapper.BannerMapper;
import com.mood.base.Pager;
import com.mood.entity.banner.Banner;
import com.mood.entity.goods.Goods;
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
public class BannerDaoImpl implements BannerDao {

   @Autowired
    private BannerMapper bannerMapper;

    @Override
    public int insert(Banner banner) {
        return bannerMapper.insert(banner);
    }

    @Override
    public int update(Banner banner) {
        return bannerMapper.updateByPrimaryKeySelective(banner);
    }

    @Override
    public int deleteById(String id) {
        return bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Banner selectById(String id) {
        return bannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Banner> selectAll(JSONObject param) {
        Example example = new Example(Banner.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object bannerType = param.get("bannerType");
            if (bannerType != null) {
                criteria.andEqualTo("bannerType", bannerType);
            }
        }
        example.setOrderByClause("sort_by asc");
        return bannerMapper.selectByExample(example);
    }

    @Override
    public List<Banner> selectAll(Banner banner, String... data) {
        Example example = new Example(Banner.class);
        Example.Criteria criteria = example.createCriteria();
        if (banner != null) {
            //拼接条件
            String keywords = banner.getKeywords();
            if (keywords != null && !"".equals(keywords)) {
                criteria.andEqualTo("keywords", keywords);
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
        return bannerMapper.selectByExample(example);
    }

    @Override
    public Pager<Banner> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Banner> result = selectAll((Banner)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
