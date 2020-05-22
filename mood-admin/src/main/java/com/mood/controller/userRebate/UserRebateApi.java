package com.mood.controller.userRebate;

import com.mood.annotation.LoginRequired;
import com.mood.base.BaseController;
import com.mood.entity.userRebate.notes.*;
import com.mood.entity.userRebate.request.*;
import com.mood.entity.userRebate.response.*;
import com.mood.userRebate.service.UserRebateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping(value = "/api/v1/userRebate", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class UserRebateApi extends BaseController {

    @Autowired
    private UserRebateService userRebateService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = UserRebateInsertNote.title, notes = UserRebateInsertNote.notes)
    @PostMapping("")
    public UserRebateInsertResponse create(@Valid @RequestBody UserRebateInsertRequest request, BindingResult error){
        return userRebateService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = UserRebateUpdateNote.title, notes = UserRebateUpdateNote.notes)
    @PostMapping("update")
    public UserRebateUpdateResponse update(@Valid @RequestBody UserRebateUpdateRequest request, BindingResult error){
        return userRebateService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = UserRebateDeleteNote.title, notes = UserRebateDeleteNote.notes)
    @PostMapping("delete")
    public UserRebateDeleteResponse delete(@Valid @RequestBody UserRebateDeleteRequest request, BindingResult error){
        return userRebateService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = UserRebateSelectNote.title, notes = UserRebateSelectNote.notes)
    @PostMapping("select")
    public UserRebateSelectResponse selete(@Valid @RequestBody UserRebateSelectRequest request, BindingResult error){
        return userRebateService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = UserRebateListNote.title, notes = UserRebateListNote.notes)
    @PostMapping("/list")
    public UserRebateListResponse list(@Valid @RequestBody UserRebateListRequest request, BindingResult error){
        return userRebateService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = UserRebatePagerNote.title, notes = UserRebatePagerNote.notes)
    @PostMapping("/pager")
    @LoginRequired
    public UserRebatePagerResponse pager(@Valid @RequestBody UserRebatePagerRequest request, BindingResult error){
        return userRebateService.selectPager(request);
    }

}
