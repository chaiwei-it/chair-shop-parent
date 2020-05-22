package com.mood.controller.goods;

import com.mood.base.BaseController;
import com.mood.entity.goodsType.notes.*;
import com.mood.entity.goodsType.request.*;
import com.mood.entity.goodsType.response.*;
import com.mood.goodsType.service.GoodsTypeService;
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
@RequestMapping(value = "/api/v1/goodsType", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class GoodsTypeApi extends BaseController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsTypeInsertNote.title, notes = GoodsTypeInsertNote.notes)
    @PostMapping("")
    public GoodsTypeInsertResponse create(@Valid @RequestBody GoodsTypeInsertRequest request, BindingResult error){
        return goodsTypeService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsTypeUpdateNote.title, notes = GoodsTypeUpdateNote.notes)
    @PostMapping("update")
    public GoodsTypeUpdateResponse update(@Valid @RequestBody GoodsTypeUpdateRequest request, BindingResult error){
        return goodsTypeService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsTypeDeleteNote.title, notes = GoodsTypeDeleteNote.notes)
    @PostMapping("delete")
    public GoodsTypeDeleteResponse delete(@Valid @RequestBody GoodsTypeDeleteRequest request, BindingResult error){
        return goodsTypeService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsTypeSelectNote.title, notes = GoodsTypeSelectNote.notes)
    @PostMapping("select")
    public GoodsTypeSelectResponse selete(@Valid @RequestBody GoodsTypeSelectRequest request, BindingResult error){
        return goodsTypeService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsTypeListNote.title, notes = GoodsTypeListNote.notes)
    @PostMapping("/list")
    public GoodsTypeListResponse list(@Valid @RequestBody GoodsTypeListRequest request, BindingResult error){
        return goodsTypeService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsTypePagerNote.title, notes = GoodsTypePagerNote.notes)
    @PostMapping("/pager")
    public GoodsTypePagerResponse pager(@Valid @RequestBody GoodsTypePagerRequest request, BindingResult error){
        return goodsTypeService.selectPager(request);
    }


}
