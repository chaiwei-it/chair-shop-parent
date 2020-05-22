package com.mood.admin.service.impl;

import com.google.common.base.Preconditions;
import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.admin.dao.AdminDao;
import com.mood.admin.service.AdminService;
import com.mood.base.Pager;
import com.mood.entity.admin.Admin;
import com.mood.entity.admin.request.LoginRequest;
import com.mood.entity.admin.request.RegisterRequest;
import com.mood.entity.admin.response.AdminInfo;
import com.mood.entity.admin.response.LoginResponse;
import com.mood.entity.admin.response.RegisterResponse;
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
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Admin insert(Admin request){
        Admin admin = OrikaMapper.map(request, Admin.class);
        admin.setId(IdGen.uuid());
        adminDao.insert(admin);
        return OrikaMapper.map(admin, Admin.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Admin update(Admin request){
        Admin admin = OrikaMapper.map(request, Admin.class);
        adminDao.update(admin);
        return OrikaMapper.map(admin, Admin.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Admin request){
        adminDao.deleteById(request.getId());
        Shift.fatal(StatusCode.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Admin select(Admin request){
        Admin admin = adminDao.selectById(request.getId());
        return OrikaMapper.map(admin, Admin.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Admin selectById(String id){
        return adminDao.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Admin selectList(Admin request){
        Admin admin = OrikaMapper.map(request, Admin.class);
        List<Admin> adminList = adminDao.selectAll(admin);
        List<Admin> detailsList = OrikaMapper.mapList(adminList, Admin.class);
        Admin response = new Admin();
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Admin selectPager(Admin request){
        Admin admin = OrikaMapper.map(request, Admin.class);
        Pager<Admin> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(admin);
        pager = adminDao.selectPager(pager);
        List<Admin> detailsList = OrikaMapper.mapList(pager.getData(), Admin.class);
        Admin response = OrikaMapper.map(pager, Admin.class);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(RegisterRequest request) {
        Preconditions.checkNotNull(request);
        final Admin dbAdmin = selectByUsername(request.getUsername());
        if (dbAdmin != null) {
            Shift.fatal(StatusCode.USER_EXISTS);
        }
        // 重新计算密码
        final Admin transientUser = OrikaMapper.map(request, Admin.class);
        final String salt = generateRandomPasswordSalt();
        final String loginPassword = digestWithSalt(request.getPassword(), salt);

        transientUser.setId(IdGen.uuid());
        transientUser.setSalt(salt);
        transientUser.setPassword(loginPassword);
        transientUser.setDelStatus(0);
        transientUser.setCreateTime(System.currentTimeMillis());
        transientUser.setUpdateTime(System.currentTimeMillis());
        transientUser.setCreatePerson(transientUser.getId());
        transientUser.setUpdatePerson(transientUser.getId());
        adminDao.insert(transientUser);
        return OrikaMapper.map(transientUser, RegisterResponse.class);
    }

    /**
     * 登录接口
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginResponse login(LoginRequest request){
        Preconditions.checkNotNull(request);
        final Admin dbAdmin = selectByUsername(request.getUsername());
        if (dbAdmin == null) {
            Shift.fatal(StatusCode.USER_NOT_EXISTS);
        }
        final String salt = dbAdmin.getSalt();
        final String loginPassword = digestWithSalt(request.getPassword(), salt);

        if (!loginPassword.equals(dbAdmin.getPassword())) {
            Shift.fatal(StatusCode.INVALID_CREDENTIAL);
        }
        String tokenStr = dbAdmin.getId() + ":" + System.currentTimeMillis();
        String token  = DESUtil.encrypt(tokenStr);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setAdminInfo(OrikaMapper.map(dbAdmin, AdminInfo.class));
        return response;
    }

    /**
     * 根据手机号获取用户
     * @param username
     * @return
     */
    @Override
    public Admin selectByUsername(String username) {
        Admin admin = new Admin();
        admin.setUsername(username);
        return adminDao.selectOne(admin);
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
