package com.mood.admin.service;

import com.mood.entity.admin.Admin;
import com.mood.entity.admin.request.LoginRequest;
import com.mood.entity.admin.request.RegisterRequest;
import com.mood.entity.admin.response.LoginResponse;
import com.mood.entity.admin.response.RegisterResponse;
import com.mood.entity.user.User;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AdminService {

    public Admin insert(Admin request);

    public Admin update(Admin request);

    public void delete(Admin request);

    public Admin select(Admin request);

    public Admin selectById(String id);

    public Admin selectList(Admin request);

    public Admin selectPager(Admin request);

    /**
     * 注册
     * @param request
     * @return
     */
    public RegisterResponse register(RegisterRequest request);

    /**
     * 登录接口
     * @param request
     * @return
     */
    public LoginResponse login(LoginRequest request);

    /**
     * 根据手机号获取用户
     * @param username
     * @return
     */
    public Admin selectByUsername(String username);
}
