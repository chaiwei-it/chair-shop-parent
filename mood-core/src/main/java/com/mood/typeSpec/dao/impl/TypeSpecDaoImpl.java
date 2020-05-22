package com.mood.typeSpec.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.typeSpec.TypeSpec;
import com.mood.typeSpec.dao.TypeSpecDao;
import com.mood.typeSpec.dao.mapper.TypeSpecMapper;
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
public class TypeSpecDaoImpl implements TypeSpecDao {

   @Autowired
    private TypeSpecMapper typeSpecMapper;

    @Override
    public int insert(TypeSpec typeSpec) {
        return typeSpecMapper.insert(typeSpec);
    }

    @Override
    public int update(TypeSpec typeSpec) {
        return typeSpecMapper.updateByPrimaryKeySelective(typeSpec);
    }

    @Override
    public int deleteById(String id) {
        return typeSpecMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TypeSpec selectById(String id) {
        return typeSpecMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TypeSpec> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<TypeSpec> selectAll(TypeSpec typeSpec, String... data) {
        Example example = new Example(TypeSpec.class);
        Example.Criteria criteria = example.createCriteria();
        if (typeSpec != null) {
            //拼接条件
            Object typeId = typeSpec.getTypeId();
            if (typeId != null) {
                criteria.andEqualTo("typeId", typeId.toString());
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
        return typeSpecMapper.selectByExample(example);
    }

    @Override
    public Pager<TypeSpec> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<TypeSpec> result = selectAll((TypeSpec)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
