package com.mood.controller.user;

import com.mood.base.BaseController;
import com.mood.entity.user.notes.*;
import com.mood.entity.user.request.*;
import com.mood.entity.user.response.*;
import com.mood.model.response.RestfulResponse;
import com.mood.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-06 17:34
 */
@RestController
@RequestMapping(value = "/api/v1/user",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class UserApi extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 添加
     * @param request
     * @param error
     * @return
     */
    @ApiOperation(value = UserInsertNote.title,notes = UserInsertNote.notes)
    @PostMapping("")
    public UserInsertResponse create(@Valid @RequestBody UserInsertRequest request, BindingResult error){
        return userService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @param error
     * @return
     */
    @ApiOperation(value = UserUpdateNote.title,notes = UserUpdateNote.notes)
    @PutMapping("")
    public UserUpdateResponse update(@Valid @RequestBody UserUpdateRequest request,BindingResult error){
        return userService.update(request);
    }


    /**
     * 删除
     * @param request
     * @param error
     */
    @ApiOperation(value = UserDeleteNote.title,notes = UserDeleteNote.notes)
    @DeleteMapping("")
    public RestfulResponse delete(@Valid @RequestBody UserDeleteRequest request, BindingResult error){
        return userService.delete(request);
    }


    /**
     * 详情
     * @param request
     * @param error
     * @return
     */
    @ApiOperation(value = UserSelectNote.title,notes = UserSelectNote.notes)
    @PostMapping("/select")
    public UserSelectResponse select(@Valid @RequestBody UserSelectRequest request,BindingResult error){
        return userService.select(request);
    }


    /**
     * 列表
     * @param request
     * @param error
     * @return
     */
    @ApiOperation(value = UserListNote.title,notes = UserListNote.notes)
    @PostMapping("/list")
    public UserListResponse list(@Valid @RequestBody UserListRequest request,BindingResult error){
        return userService.selectList(request);
    }


    /**
     * 分页
     * @param request
     * @param error
     * @return
     */
    @ApiOperation(value = UserPagerNote.title,notes = UserPagerNote.notes)
    @PostMapping("/pager")
    public UserPagerResponse pager(@Valid @RequestBody UserPagerRequest request,BindingResult error){
        return userService.selectPager(request);
    }






}
