package com.mood.controller.advert;

import com.mood.advert.service.AdvertService;
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
@RequestMapping("/api/{version}/advert")
public class AdvertApi extends BaseController {

    @Autowired
    private AdvertService advertService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertInsertNote.title, notes = AdvertInsertNote.notes)
    @PostMapping("")
    public AdvertInsertResponse create(@Valid @RequestBody AdvertInsertRequest request, BindingResult error){
        return advertService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertUpdateNote.title, notes = AdvertUpdateNote.notes)
    @PutMapping("")
    public AdvertUpdateResponse update(@Valid @RequestBody AdvertUpdateRequest request, BindingResult error){
        return advertService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertDeleteNote.title, notes = AdvertDeleteNote.notes)
    @DeleteMapping("")
    public void delete(@Valid @RequestBody AdvertDeleteRequest request, BindingResult error){
        advertService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertSelectNote.title, notes = AdvertSelectNote.notes)
    @PostMapping("select")
    public AdvertSelectResponse selete(@Valid @RequestBody AdvertSelectRequest request, BindingResult error){
        return advertService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertListNote.title, notes = AdvertListNote.notes)
    @PostMapping("/list")
    public AdvertListResponse list(@Valid @RequestBody AdvertListRequest request, BindingResult error){
        return advertService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = AdvertPagerNote.title, notes = AdvertPagerNote.notes)
    @PostMapping("/pager")
    public AdvertPagerResponse pager(@Valid @RequestBody AdvertPagerRequest request, BindingResult error){
        return advertService.selectPager(request);
    }
}
