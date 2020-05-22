package com.mood.controller.putforward;

import com.mood.base.BaseController;
import com.mood.entity.putforward.notes.*;
import com.mood.entity.putforward.request.*;
import com.mood.entity.putforward.response.*;
import com.mood.putforward.service.PutforwardService;
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
@RequestMapping(value = "/api/v1/putforward", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class PutforwardApi extends BaseController {

    @Autowired
    private PutforwardService putforwardService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = PutforwardInsertNote.title, notes = PutforwardInsertNote.notes)
    @PostMapping("")
    public PutforwardInsertResponse create(@Valid @RequestBody PutforwardInsertRequest request, BindingResult error){
        return putforwardService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = PutforwardUpdateNote.title, notes = PutforwardUpdateNote.notes)
    @PostMapping("update")
    public PutforwardUpdateResponse update(@Valid @RequestBody PutforwardUpdateRequest request, BindingResult error){
        return putforwardService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = PutforwardDeleteNote.title, notes = PutforwardDeleteNote.notes)
    @PostMapping("delete")
    public PutforwardDeleteResponse delete(@Valid @RequestBody PutforwardDeleteRequest request, BindingResult error){
        return putforwardService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = PutforwardSelectNote.title, notes = PutforwardSelectNote.notes)
    @PostMapping("select")
    public PutforwardSelectResponse selete(@Valid @RequestBody PutforwardSelectRequest request, BindingResult error){
        return putforwardService.select(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = PutforwardListNote.title, notes = PutforwardListNote.notes)
    @PostMapping("/list")
    public PutforwardListResponse list(@Valid @RequestBody PutforwardListRequest request, BindingResult error){
        return putforwardService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = PutforwardPagerNote.title, notes = PutforwardPagerNote.notes)
    @PostMapping("/pager")
    public PutforwardPagerResponse pager(@Valid @RequestBody PutforwardPagerRequest request, BindingResult error){
        return putforwardService.selectPager(request);
    }


}
