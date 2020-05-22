package com.mood.controller.spec;

import com.mood.base.BaseController;
import com.mood.entity.specValue.notes.*;
import com.mood.entity.specValue.request.*;
import com.mood.entity.specValue.response.*;
import com.mood.specValue.service.SpecValueService;
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
@RequestMapping(value = "/api/v1/specValue", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class SpecValueApi extends BaseController {

    @Autowired
    private SpecValueService specValueService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = SpecValueInsertNote.title, notes = SpecValueInsertNote.notes)
    @PostMapping("")
    public SpecValueInsertResponse create(@Valid @RequestBody SpecValueInsertRequest request, BindingResult error){
        return specValueService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = SpecValueUpdateNote.title, notes = SpecValueUpdateNote.notes)
    @PostMapping("update")
    public SpecValueUpdateResponse update(@Valid @RequestBody SpecValueUpdateRequest request, BindingResult error){
        return specValueService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = SpecValueDeleteNote.title, notes = SpecValueDeleteNote.notes)
    @PostMapping("delete")
    public SpecValueDeleteResponse delete(@Valid @RequestBody SpecValueDeleteRequest request, BindingResult error){
        return specValueService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = SpecValueSelectNote.title, notes = SpecValueSelectNote.notes)
    @PostMapping("select")
    public SpecValueSelectResponse selete(@Valid @RequestBody SpecValueSelectRequest request, BindingResult error){
        return specValueService.select(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = SpecValueListNote.title, notes = SpecValueListNote.notes)
    @PostMapping("/list")
    public SpecValueListResponse list(@Valid @RequestBody SpecValueListRequest request, BindingResult error){
        return specValueService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = SpecValuePagerNote.title, notes = SpecValuePagerNote.notes)
    @PostMapping("/pager")
    public SpecValuePagerResponse pager(@Valid @RequestBody SpecValuePagerRequest request, BindingResult error){
        return specValueService.selectPager(request);
    }


}
