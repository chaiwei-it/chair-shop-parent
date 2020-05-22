package com.mood.userOpen.service.impl;

import com.mood.base.Pager;
import com.mood.entity.rabate.UserOpen;
import com.mood.userOpen.dao.UserOpenDao;
import com.mood.userOpen.service.UserOpenService;
import com.mood.utils.IdGen;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class UserOpenServiceImpl implements UserOpenService {

    @Autowired
    private UserOpenDao userOpenDao;


    @Override
    public int insert(UserOpen userOpen) {
        userOpen.setId(IdGen.uuid());
        userOpen.setCreateTime(System.currentTimeMillis());
        userOpen.setUpdateTime(System.currentTimeMillis());
        userOpen.setDelStatus(0);
        return userOpenDao.insert(userOpen);
    }

    @Override
    public int update(UserOpen userOpen) {
        userOpen.setUpdateTime(System.currentTimeMillis());
        return userOpenDao.update(userOpen);
    }

    @Override
    public int deleteById(String id) {
        return userOpenDao.deleteById(id);
    }

    @Override
    public UserOpen selectById(String id) {
        return userOpenDao.selectById(id);
    }

    @Override
    public List<UserOpen> selectAll(JSONObject param) {
        return userOpenDao.selectAll(param);
    }

    @Override
    public Pager<UserOpen> selectPager(Pager pager){
        return userOpenDao.selectPager(pager);
    }

    @Override
    public void getOpenId(String userId, String unionId, String openId, Integer appId){
        net.sf.json.JSONObject param = new net.sf.json.JSONObject();
        param.put("openId", openId);
        param.put("unionId", unionId);
        param.put("appId", appId);
        List<UserOpen> userOpenList =  selectAll(param);
        if(userOpenList.size() == 0){
            UserOpen userOpen = new UserOpen();
            userOpen.setAppId(appId);
            userOpen.setUserId(userId);
            userOpen.setOpenId(openId);
            userOpen.setUnionId(unionId);
            insert(userOpen);
        }
    }

    @Override
    public UserOpen selectByUserId(String userId){
        JSONObject param = new JSONObject();
        param.put("userId", userId);
        param.put("appId", 1);
        List<UserOpen> userOpenList = selectAll(param);
        if(userOpenList.size() > 0){
            return userOpenList.get(0);
        }
        return null;
    }

}
