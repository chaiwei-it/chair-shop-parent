package com.mood.address.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.address.dao.AddressDao;
import com.mood.address.dao.mapper.AddressMapper;
import com.mood.base.Pager;
import com.mood.entity.address.Address;
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
public class AddressDaoImpl implements AddressDao {

   @Autowired
    private AddressMapper addressMapper;

    @Override
    public int insert(Address address) {
        return addressMapper.insert(address);
    }

    @Override
    public int update(Address address) {
        return addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public int deleteById(String id) {
        return addressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Address selectById(String id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Address> selectAll(JSONObject param) {
        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object username = param.get("username");
            if (username != null) {
                criteria.andEqualTo("username", "%" + username.toString() + "%");
            }
            Object userId = param.get("userId");
            if (userId != null) {
                criteria.andEqualTo("userId", userId);
            }
        }
        example.setOrderByClause("default_status desc");
        return addressMapper.selectByExample(example);
    }

    @Override
    public List<Address> selectAll(Address test, String... data) {
        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        if (test != null) {
            //拼接条件
            String userId = test.getUserId();
            if (userId != null && !"".equals(userId)) {
                criteria.andEqualTo("userId", userId);
            }
        }
        String orderBy = "";
        if(data.length > 0){
            orderBy = data[0];
        }else{
            orderBy = "default_status desc";
        }
        if(!"".equals(orderBy)){
            example.setOrderByClause(orderBy);
        }
        return addressMapper.selectByExample(example);
    }

    @Override
    public Pager<Address> selectPager(Pager pager){
        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Address> result = addressMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
