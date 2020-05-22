package com.mood.controller.extension;

import com.mood.base.BaseController;
import com.mood.entity.extensionGoods.notes.*;
import com.mood.entity.extensionGoods.request.*;
import com.mood.entity.extensionGoods.response.*;
import com.mood.extensionGoods.service.ExtensionGoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/{version}/extension")
public class ExtensionApi extends BaseController {

    @Autowired
    private ExtensionGoodsService extensionService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = ExtensionInsertNote.title, notes = ExtensionInsertNote.notes)
    @PostMapping("")
    public ExtensionInsertResponse create(@Valid @RequestBody ExtensionInsertRequest request, BindingResult error){
        return extensionService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = ExtensionUpdateNote.title, notes = ExtensionUpdateNote.notes)
    @PostMapping("update")
    public ExtensionUpdateResponse update(@Valid @RequestBody ExtensionUpdateRequest request, BindingResult error){
        return extensionService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = ExtensionDeleteNote.title, notes = ExtensionDeleteNote.notes)
    @PostMapping("delete")
    public ExtensionDeleteResponse delete(@Valid @RequestBody ExtensionDeleteRequest request, BindingResult error){
        return extensionService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = ExtensionSelectNote.title, notes = ExtensionSelectNote.notes)
    @PostMapping("select")
    public ExtensionSelectResponse selete(@Valid @RequestBody ExtensionSelectRequest request, BindingResult error){
        return extensionService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = ExtensionListNote.title, notes = ExtensionListNote.notes)
    @PostMapping("/list")
    public ExtensionListResponse list(@Valid @RequestBody ExtensionListRequest request, BindingResult error){
        return extensionService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = ExtensionPagerNote.title, notes = ExtensionPagerNote.notes)
    @PostMapping("/pager")
    public ExtensionPagerResponse pager(@Valid @RequestBody ExtensionPagerRequest request, BindingResult error){
        return extensionService.selectPager(request);
    }
}
