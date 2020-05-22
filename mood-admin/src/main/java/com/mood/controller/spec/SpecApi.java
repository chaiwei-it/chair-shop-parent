package com.mood.controller.spec;

import com.mood.base.BaseController;
import com.mood.entity.spec.notes.*;
import com.mood.entity.spec.request.*;
import com.mood.entity.spec.response.*;
import com.mood.spec.service.SpecService;
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
@RequestMapping(value = "/api/v1/spec", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class SpecApi extends BaseController {

    @Autowired
    private SpecService specService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = SpecInsertNote.title, notes = SpecInsertNote.notes)
    @PostMapping("")
    public SpecInsertResponse create(@Valid @RequestBody SpecInsertRequest request, BindingResult error){
        return specService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = SpecUpdateNote.title, notes = SpecUpdateNote.notes)
    @PostMapping("update")
    public SpecUpdateResponse update(@Valid @RequestBody SpecUpdateRequest request, BindingResult error){
        return specService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = SpecDeleteNote.title, notes = SpecDeleteNote.notes)
    @PostMapping("delete")
    public SpecDeleteResponse delete(@Valid @RequestBody SpecDeleteRequest request, BindingResult error){
        return specService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = SpecSelectNote.title, notes = SpecSelectNote.notes)
    @PostMapping("select")
    public SpecSelectResponse selete(@Valid @RequestBody SpecSelectRequest request, BindingResult error){
        return specService.select(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = SpecListNote.title, notes = SpecListNote.notes)
    @PostMapping("/list")
    public SpecListResponse list(@Valid @RequestBody SpecListRequest request, BindingResult error){
        return specService.selectList(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = SpecListNote.title, notes = SpecListNote.notes)
    @PostMapping("/typeSpecList")
    public SpecValueDatailsListResponse list(@Valid @RequestBody SpecValueDatailsListRequest request, BindingResult error){
        return specService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = SpecPagerNote.title, notes = SpecPagerNote.notes)
    @PostMapping("/pager")
    public SpecPagerResponse pager(@Valid @RequestBody SpecPagerRequest request, BindingResult error){
        return specService.selectPager(request);
    }


}
