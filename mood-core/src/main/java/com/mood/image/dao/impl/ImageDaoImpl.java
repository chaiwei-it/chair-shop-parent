package com.mood.image.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.rabate.Image;
import com.mood.image.dao.ImageDao;
import com.mood.image.dao.mapper.ImageMapper;
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
public class ImageDaoImpl implements ImageDao {

   @Autowired
    private ImageMapper imageMapper;

    @Override
    public int insert(Image image) {
        return imageMapper.insert(image);
    }

    @Override
    public int update(Image image) {
        return imageMapper.updateByPrimaryKeySelective(image);
    }

    @Override
    public int deleteById(String id) {
        return imageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Image selectById(String id) {
        return imageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Image> selectAll(JSONObject param) {
        Example example = new Example(Image.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object name = param.get("name");
            if (name != null) {
                criteria.andLike("name", "%" + name.toString() + "%");
            }
        }
        example.setOrderByClause("create_time desc");
        return imageMapper.selectByExample(example);
    }

    @Override
    public Pager<Image> selectPager(Pager pager){
        Example example = new Example(Image.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Image> result = imageMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
