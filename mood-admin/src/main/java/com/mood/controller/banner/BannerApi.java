package com.mood.controller.banner;

import com.mood.banner.service.BannerService;
import com.mood.base.BaseController;
import com.mood.entity.banner.notes.*;
import com.mood.entity.banner.request.*;
import com.mood.entity.banner.response.*;
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
@RequestMapping("/api/{version}/banner")
public class BannerApi extends BaseController {

    @Autowired
    private BannerService bannerService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = BannerInsertNote.title, notes = BannerInsertNote.notes)
    @PostMapping("")
    public BannerInsertResponse create(@Valid @RequestBody BannerInsertRequest request, BindingResult error){
        return bannerService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = BannerUpdateNote.title, notes = BannerUpdateNote.notes)
    @PostMapping("update")
    public BannerUpdateResponse update(@Valid @RequestBody BannerUpdateRequest request, BindingResult error){
        return bannerService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = BannerDeleteNote.title, notes = BannerDeleteNote.notes)
    @PostMapping("delete")
    public BannerDeleteResponse delete(@Valid @RequestBody BannerDeleteRequest request, BindingResult error){
        return bannerService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = BannerSelectNote.title, notes = BannerSelectNote.notes)
    @PostMapping("select")
    public BannerSelectResponse selete(@Valid @RequestBody BannerSelectRequest request, BindingResult error){
        return bannerService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = BannerListNote.title, notes = BannerListNote.notes)
    @PostMapping("/list")
    public BannerListResponse list(@Valid @RequestBody BannerListRequest request, BindingResult error){
        return bannerService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = BannerPagerNote.title, notes = BannerPagerNote.notes)
    @PostMapping("/pager")
    public BannerPagerResponse pager(@Valid @RequestBody BannerPagerRequest request, BindingResult error){
        return bannerService.selectPager(request);
    }
}
