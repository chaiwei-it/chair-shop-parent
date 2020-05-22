package com.mood.wxpay.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.rabate.Wxpay;
import com.mood.wxpay.dao.WxpayDao;
import com.mood.wxpay.dao.mapper.WxpayMapper;
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
public class WxpayDaoImpl implements WxpayDao {

   @Autowired
    private WxpayMapper wxpayMapper;

    @Override
    public int insert(Wxpay wxpay) {
        return wxpayMapper.insert(wxpay);
    }

    @Override
    public int update(Wxpay wxpay) {
        return wxpayMapper.updateByPrimaryKeySelective(wxpay);
    }

    @Override
    public int deleteById(String id) {
        return wxpayMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Wxpay selectById(String id) {
        return wxpayMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Wxpay> selectAll(JSONObject param) {
        Example example = new Example(Wxpay.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object orderType = param.get("orderType");
            if (orderType != null) {
                criteria.andEqualTo("orderType", orderType.toString());
            }
            Object orderId = param.get("orderId");
            if (orderId != null) {
                criteria.andEqualTo("orderId", orderId.toString());
            }
        }
        example.setOrderByClause("create_time desc");
        return wxpayMapper.selectByExample(example);
    }

    @Override
    public Pager<Wxpay> selectPager(Pager pager){
        Example example = new Example(Wxpay.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Wxpay> result = wxpayMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
