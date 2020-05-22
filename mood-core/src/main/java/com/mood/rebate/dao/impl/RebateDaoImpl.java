package com.mood.rebate.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.rabate.Rebate;
import com.mood.rebate.dao.RebateDao;
import com.mood.rebate.dao.mapper.RebateMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Repository
public class RebateDaoImpl implements RebateDao {

   @Autowired
    private RebateMapper rebateMapper;

    @Override
    public int insert(Rebate rebate) {
        return rebateMapper.insert(rebate);
    }

    @Override
    public int update(Rebate rebate) {
        return rebateMapper.updateByPrimaryKeySelective(rebate);
    }

    @Override
    public int deleteById(String id) {
        return rebateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Rebate selectById(String id) {
        return rebateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Rebate> selectAll(JSONObject param) {
        Example example = new Example(Rebate.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object rebateNum = param.get("rebateNum");
            if(rebateNum != null){
                Calendar cal = Calendar.getInstance();
                String rabateData = "";
                String year = cal.get(1) + "";
                String month = cal.get(2) + 1 + "";
                if(month.length() == 1){
                    month = "0" + month;
                }
                String day = cal.get(5) + "";
                if(day.length() == 1){
                    day = "0" + day;
                }
                if("0".equals(rebateNum)){
                    rabateData = year + "-" + month + "-" + day;
                    criteria.andLike("rabateData", rabateData + "%");
                }else if("1".equals(rebateNum)){
                    rabateData = year + "-" + month;
                    criteria.andLike("rabateData", rabateData + "%");
                }
            }
            Object userId = param.get("userId");
            if (userId != null) {
                criteria.andEqualTo("userId", userId.toString());
            }
            Object orderId = param.get("orderId");
            if (orderId != null) {
                criteria.andEqualTo("orderId", orderId.toString());
            }
            Object status = param.get("status");
            if (status != null) {
                criteria.andEqualTo("status", status);
            }
            Object rebateType = param.get("rebateType");
            if (rebateType != null) {
                criteria.andEqualTo("rebateType", rebateType);
            }
        }
        example.setOrderByClause("update_time desc");
        return rebateMapper.selectByExample(example);
    }

    @Override
    public Pager<Rebate> selectPager(Pager pager){
        Example example = new Example(Rebate.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Rebate> result = rebateMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public List<Rebate> getRebateList(String rebateNum){
        Example example = new Example(Rebate.class);
        Example.Criteria criteria = example.createCriteria();
        Calendar cal = Calendar.getInstance();
        String rabateData = "";
        //拼接条件
        example.setOrderByClause("create_time asc");
        return rebateMapper.selectByExample(example);
    }

}
