package com.mood.extensionGoods.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.extensionGoods.Extension;
import com.mood.extensionGoods.dao.ExtensionGoodsDao;
import com.mood.extensionGoods.dao.mapper.ExtensionGoodsMapper;
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
public class ExtensionGoodsDaoImpl implements ExtensionGoodsDao {

   @Autowired
    private ExtensionGoodsMapper extensionGoodsMapper;

    @Override
    public int insert(Extension extensionGoods) {
        return extensionGoodsMapper.insert(extensionGoods);
    }

    @Override
    public int update(Extension extensionGoods) {
        return extensionGoodsMapper.updateByPrimaryKeySelective(extensionGoods);
    }

    @Override
    public int deleteById(String id) {
        return extensionGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Extension selectById(String id) {
        return extensionGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Extension> selectAll(JSONObject param){
        Example example = new Example(Extension.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object keywords = param.get("keywords");
            if (keywords != null && !"".equals(keywords)) {
                criteria.andLike("keywords", keywords.toString());
            }
        }
        String orderBy  = "id asc";
        example.setOrderByClause(orderBy);
        return extensionGoodsMapper.selectByExample(example);
    }

    @Override
    public List<Extension> selectAll(Extension extensionGoods, String... data) {
        Example example = new Example(Extension.class);
        Example.Criteria criteria = example.createCriteria();
        if (extensionGoods != null) {
            //拼接条件
            Object keywords = extensionGoods.getKeywords();
            if (keywords != null) {
                criteria.andLike("keywords", keywords.toString());
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
        return extensionGoodsMapper.selectByExample(example);
    }

    @Override
    public Pager<Extension> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Extension> result = selectAll((Extension)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
