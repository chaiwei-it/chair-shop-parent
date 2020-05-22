package com.mood.controller.advert;

import com.mood.advertClass.service.AdvertClassService;
import com.mood.base.BaseController;
import com.mood.entity.advert.notes.*;
import com.mood.entity.advert.request.*;
import com.mood.entity.advert.response.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/advertClass")
public class AdvertClassApi extends BaseController {

    @Autowired
    private AdvertClassService advertClassService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertClassInsertNote.title, notes = AdvertClassInsertNote.notes)
    @PostMapping("")
    public AdvertClassInsertResponse create(@Valid @RequestBody AdvertClassInsertRequest request, BindingResult error){
        return advertClassService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertClassUpdateNote.title, notes = AdvertClassUpdateNote.notes)
    @PutMapping("")
    public AdvertClassUpdateResponse update(@Valid @RequestBody AdvertClassUpdateRequest request, BindingResult error){
        return advertClassService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertClassDeleteNote.title, notes = AdvertClassDeleteNote.notes)
    @DeleteMapping("")
    public void delete(@Valid @RequestBody AdvertClassDeleteRequest request, BindingResult error){
        advertClassService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertClassSelectNote.title, notes = AdvertClassSelectNote.notes)
    @PostMapping("select")
    public AdvertClassSelectResponse selete(@Valid @RequestBody AdvertClassSelectRequest request, BindingResult error){
        return advertClassService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertClassListNote.title, notes = AdvertClassListNote.notes)
    @PostMapping("/list")
    public AdvertClassListResponse list(@Valid @RequestBody AdvertClassListRequest request, BindingResult error){
        return advertClassService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertClassPagerNote.title, notes = AdvertClassPagerNote.notes)
    @PostMapping("/pager")
    public AdvertClassPagerResponse pager(@Valid @RequestBody AdvertClassPagerRequest request, BindingResult error){
        return advertClassService.selectPager(request);
    }
}
