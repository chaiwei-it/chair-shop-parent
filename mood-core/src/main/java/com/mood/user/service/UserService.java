package com.mood.user.service;

import com.mood.entity.user.User;
import com.mood.entity.user.request.*;
import com.mood.entity.user.response.*;
import com.mood.model.response.RestfulResponse;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-06 17:39
 */
public interface UserService {

    UserInsertResponse insert(UserInsertRequest request);

    UserUpdateResponse update(UserUpdateRequest request);

    RestfulResponse delete(UserDeleteRequest request);

    UserSelectResponse select(UserSelectRequest request);

    UserListResponse selectList(UserListRequest request);

    UserPagerResponse selectPager(UserPagerRequest request);

    /**
     * 根据手机号获取用户
     * @param mobile
     * @return
     */
    public User selectByMobile(String mobile);


}
