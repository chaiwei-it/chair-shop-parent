package com.mood.user.service.impl;

import com.google.common.base.Preconditions;
import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.user.User;
import com.mood.entity.user.request.*;
import com.mood.entity.user.response.*;
import com.mood.model.response.RestfulResponse;
import com.mood.user.dao.UserDao;
import com.mood.user.service.UserService;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
import com.mood.utils.des.DESUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-06 18:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserInsertResponse insert(UserInsertRequest request) {
        User user = OrikaMapper.map(request,User.class);
        user.setId(IdGen.uuid());
        user.setCreateTime(System.currentTimeMillis());
        userDao.insert(user);
        return OrikaMapper.map(user,UserInsertResponse.class);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserUpdateResponse update(UserUpdateRequest request) {
        User oldUser = userDao.selectById(request.getId());
        if(oldUser == null){
            Shift.fatal(StatusCode.USER_NEVER);
        }
        User user = OrikaMapper.map(request,User.class);
        user.setUpdateTime(System.currentTimeMillis());
        if(!"".equals(user.getPassword())) {
            user.setPassword(digestWithSalt(user.getPassword(), oldUser.getSalt()));
        }
        userDao.update(user);
        return OrikaMapper.map(user,UserUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RestfulResponse delete(UserDeleteRequest request) {
        User user = OrikaMapper.map(request,User.class);
        userDao.deleteById(user.getId());
        return new RestfulResponse();
//        Shift.fatal(StatusCode.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserSelectResponse select(UserSelectRequest request) {
        User user = userDao.selectById(request.getId());
        return OrikaMapper.map(user,UserSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserListResponse selectList(UserListRequest request) {
        User user = OrikaMapper.map(request,User.class);
        List<User> userList = userDao.selectAll(user);
        List<UserDetailsResponse> detailsList = OrikaMapper.mapList(userList,UserDetailsResponse.class);
        UserListResponse response = new UserListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserPagerResponse selectPager(UserPagerRequest request) {
        User user = OrikaMapper.map(request,User.class);
        Pager<User> pager = OrikaMapper.map(request,Pager.class);
        pager.setObject(user);
        pager = userDao.selectPager(pager);
        List<UserDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(),UserDetailsResponse.class);
        UserPagerResponse response = OrikaMapper.map(pager,UserPagerResponse.class);
        response.setList(detailsList);
        response.setTotal(pager.getTotal());
        return response;
    }




    /**
     * 根据手机号获取用户
     * @param mobile
     * @return
     */
    @Override
    public User selectByMobile(String mobile) {
        User user = new User();
        user.setMobile(mobile);
        user.setDelStatus(0);
        user.setStatus(1);
        return userDao.selectOne(user);
    }

    private String digestWithSalt(String content, String key) {
        String result = content;
        for (int i = 0; i < 5; i++) {
            result = DigestUtils.sha256Hex(result + key);
        }
        return result;
    }

    private String generateRandomPasswordSalt() {
        return DigestUtils.sha256Hex(String.valueOf(System.nanoTime()));
    }
}
