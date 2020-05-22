package com.mood.controller.admin;

import com.mood.admin.service.AdminService;
import com.mood.entity.admin.notes.RegisterNote;
import com.mood.entity.admin.request.RegisterRequest;
import com.mood.entity.admin.response.RegisterResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 模块
 *
 * @author chaiwei
 * @time 2018-06-07 17:21
 */
@RestController
@RequestMapping(value = "/api/v1",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class RegisterApi {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = RegisterNote.title, notes = RegisterNote.notes)
    @PostMapping("register")
    public RegisterResponse create(@Valid @RequestBody RegisterRequest request, BindingResult error){
        return adminService.register(request);

    }

}
