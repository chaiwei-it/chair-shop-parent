package com.mood.userRebate.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.spec.Spec;
import com.mood.entity.userRebate.UserRebate;
import com.mood.userRebate.dao.UserRebateDao;
import com.mood.userRebate.dao.mapper.UserRebateMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Repository
public class UserRebateDaoImpl implements UserRebateDao {

   @Autowired
    private UserRebateMapper userRebateMapper;

    @Override
    public int insert(UserRebate userRebate) {
        return userRebateMapper.insert(userRebate);
    }

    @Override
    public int update(UserRebate userRebate) {
        return userRebateMapper.updateByPrimaryKeySelective(userRebate);
    }

    @Override
    public int deleteById(String id) {
        return userRebateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserRebate selectById(String id) {
        return userRebateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserRebate> selectAll(JSONObject param) {
        Example example = new Example(UserRebate.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object username = param.get("username");
            if (username != null) {
                criteria.andLike("username", "%" + username.toString() + "%");
            }
//            Object userId = param.get("userId");
//            if (userId != null) {
//                criteria.andEqualTo("userId", userId.toString());
//            }
            Object userNum = param.get("userNum");
            if (userNum != null) {
                criteria.andEqualTo("userNum", userNum.toString());
            }
            Object parentId = param.get("parentId");
            if (parentId != null) {
                criteria.andEqualTo("parentId", parentId.toString());
            }
            Object unionid = param.get("unionid");
            if (unionid != null) {
                criteria.andEqualTo("unionid", unionid.toString());
            }
            Object time = param.get("time");
            if (time != null) {
                if("today".equals(time)){
                    criteria.andLike("createDate", getToday() + "%");

                }else if("yesterday".equals(time)){
                    criteria.andLike("createDate", getYesterday() + "%");
                }
            }
        }
        example.setOrderByClause("create_time desc");
        return userRebateMapper.selectByExample(example);
    }

    public static String getYesterday(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    public static String getToday() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    @Override
    public List<UserRebate> selectAll(UserRebate userRebate, String... data) {
        Example example = new Example(UserRebate.class);
        Example.Criteria criteria = example.createCriteria();
        if (userRebate != null) {
            //拼接条件
            Object username = userRebate.getUsername();
            if (username != null && !"".equals(username)) {
                criteria.andLike("username", "%" + username.toString() + "%");
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
        return userRebateMapper.selectByExample(example);
    }

    @Override
    public Pager<UserRebate> selectPager(Pager pager){
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<UserRebate> result = selectAll((UserRebate)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
