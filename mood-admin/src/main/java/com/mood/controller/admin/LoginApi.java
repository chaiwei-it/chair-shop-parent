package com.mood.controller.admin;

import com.mood.admin.service.AdminService;
import com.mood.annotation.LoginRequired;
import com.mood.base.BaseController;
import com.mood.entity.admin.Admin;
import com.mood.entity.admin.notes.LoginNote;
import com.mood.entity.admin.request.LoginRequest;
import com.mood.entity.admin.response.AdminInfo;
import com.mood.entity.admin.response.LoginResponse;
import com.mood.utils.OrikaMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 模块
 *
 * @author chaiwei
 * @time 2018-06-07 17:21
 */
@RestController
@RequestMapping(value = "/api/v1",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class LoginApi extends BaseController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = LoginNote.title, notes = LoginNote.notes)
    @PostMapping("login")
    public LoginResponse create(@Valid @RequestBody LoginRequest request, BindingResult error){
        return adminService.login(request);
    }

    @ApiOperation(value = LoginNote.title, notes = LoginNote.notes)
    @PostMapping("info")
    @LoginRequired
    public AdminInfo info(HttpServletRequest request
                          ){
        String adminId = getUserId(request);
        Admin admin = adminService.selectById(adminId);
        return OrikaMapper.map(admin, AdminInfo.class);
    }

}
