package com.mood.controller.goods;

import com.mood.base.BaseController;
import com.mood.entity.goodsClass.notes.*;
import com.mood.entity.goodsClass.request.*;
import com.mood.entity.goodsClass.response.*;
import com.mood.goodsClass.service.GoodsClassService;
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
@RequestMapping(value = "/api/v1/goodsClass", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class GoodsClassApi extends BaseController {

    @Autowired
    private GoodsClassService goodsClassService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsClassInsertNote.title, notes = GoodsClassInsertNote.notes)
    @PostMapping("")
    public GoodsClassInsertResponse create(@Valid @RequestBody GoodsClassInsertRequest request, BindingResult error){
        return goodsClassService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsClassUpdateNote.title, notes = GoodsClassUpdateNote.notes)
    @PostMapping("update")
    public GoodsClassUpdateResponse update(@Valid @RequestBody GoodsClassUpdateRequest request, BindingResult error){
        return goodsClassService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsClassDeleteNote.title, notes = GoodsClassDeleteNote.notes)
    @PostMapping("delete")
    public GoodsClassDeleteResponse delete(@Valid @RequestBody GoodsClassDeleteRequest request, BindingResult error){
        return goodsClassService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsClassSelectNote.title, notes = GoodsClassSelectNote.notes)
    @PostMapping("select")
    public GoodsClassSelectResponse selete(@Valid @RequestBody GoodsClassSelectRequest request, BindingResult error){
        return goodsClassService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsClassListNote.title, notes = GoodsClassListNote.notes)
    @PostMapping("/list")
    public GoodsClassListResponse list(@Valid @RequestBody GoodsClassListRequest request, BindingResult error){
        return goodsClassService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsClassPagerNote.title, notes = GoodsClassPagerNote.notes)
    @PostMapping("/pager")
    public GoodsClassPagerResponse pager(@Valid @RequestBody GoodsClassPagerRequest request, BindingResult error){
        return goodsClassService.selectPager(request);
    }


}
