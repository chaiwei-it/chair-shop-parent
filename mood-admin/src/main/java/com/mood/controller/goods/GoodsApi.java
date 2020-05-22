package com.mood.controller.goods;

import com.mood.base.BaseController;
import com.mood.entity.goods.notes.*;
import com.mood.entity.goods.request.*;
import com.mood.entity.goods.response.*;
import com.mood.goods.service.GoodsService;
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
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping(value = "/api/v1/goods", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class GoodsApi extends BaseController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsInsertNote.title, notes = GoodsInsertNote.notes)
    @PostMapping("")
    public GoodsInsertResponse create(@Valid @RequestBody GoodsInsertRequest request, BindingResult error){
        return goodsService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsUpdateNote.title, notes = GoodsUpdateNote.notes)
    @PostMapping("update")
    public GoodsUpdateResponse update(@Valid @RequestBody GoodsUpdateRequest request, BindingResult error){
        return goodsService.update(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsUpdateNote.title, notes = GoodsUpdateNote.notes)
    @PostMapping("updateStatus")
    public GoodsUpdateStatusResponse updateStatus(@Valid @RequestBody GoodsUpdateStatusRequest request, BindingResult error){
        return goodsService.updateStatus(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsDeleteNote.title, notes = GoodsDeleteNote.notes)
    @PostMapping("delete")
    public GoodsDeleteResponse delete(@Valid @RequestBody GoodsDeleteRequest request, BindingResult error){
        return goodsService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsSelectNote.title, notes = GoodsSelectNote.notes)
    @PostMapping("select")
    public GoodsSelectResponse selete(@Valid @RequestBody GoodsSelectRequest request, BindingResult error){
        return goodsService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsListNote.title, notes = GoodsListNote.notes)
    @PostMapping("/list")
    public GoodsListResponse list(@Valid @RequestBody GoodsListRequest request, BindingResult error){
        return goodsService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsPagerNote.title, notes = GoodsPagerNote.notes)
    @PostMapping("/pager")
    public GoodsPagerResponse pager(@Valid @RequestBody GoodsPagerRequest request, BindingResult error){
        return goodsService.selectPager(request);
    }


}
