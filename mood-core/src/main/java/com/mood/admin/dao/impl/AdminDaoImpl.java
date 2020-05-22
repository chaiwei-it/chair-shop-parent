package com.mood.admin.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.admin.dao.AdminDao;
import com.mood.admin.dao.mapper.AdminMapper;
import com.mood.base.Pager;
import com.mood.entity.admin.Admin;
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
public class AdminDaoImpl implements AdminDao {

   @Autowired
    private AdminMapper adminMapper;

    @Override
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public int deleteById(String id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Admin selectById(String id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Admin> selectAll(Admin admin, String... data) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        if (admin != null) {
            //拼接条件
//            Object name = admin.getName();
//            if (name != null) {
//                criteria.andLike("name", "%" + name.toString() + "%");
//            }
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
        return adminMapper.selectByExample(example);
    }

    @Override
    public Pager<Admin> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Admin> result = selectAll((Admin)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public Admin selectOne(Admin admin){
        return adminMapper.selectOne(admin);
    }

}
