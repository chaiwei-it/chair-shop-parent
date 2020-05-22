package com.mood.controller.recommend;


import com.mood.base.BaseController;
import com.mood.entity.recommend.notes.*;
import com.mood.entity.recommend.request.*;
import com.mood.entity.recommend.response.*;
import com.mood.recommend.service.RecommendService;
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
@RequestMapping("/api/{version}/recommend")
public class RecommendApi extends BaseController {

    @Autowired
    private RecommendService recommendService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendInsertNote.title, notes = RecommendInsertNote.notes)
    @PostMapping("")
    public RecommendInsertResponse create(@Valid @RequestBody RecommendInsertRequest request, BindingResult error){
        return recommendService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendUpdateNote.title, notes = RecommendUpdateNote.notes)
    @PutMapping("")
    public RecommendUpdateResponse update(@Valid @RequestBody RecommendUpdateRequest request, BindingResult error){
        return recommendService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendDeleteNote.title, notes = RecommendDeleteNote.notes)
    @DeleteMapping("")
    public void delete(@Valid @RequestBody RecommendDeleteRequest request, BindingResult error){
        recommendService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendSelectNote.title, notes = RecommendSelectNote.notes)
    @PostMapping("select")
    public RecommendSelectResponse selete(@Valid @RequestBody RecommendSelectRequest request, BindingResult error){
        return recommendService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendListNote.title, notes = RecommendListNote.notes)
    @PostMapping("/list")
    public RecommendListResponse list(@Valid @RequestBody RecommendListRequest request, BindingResult error){
        return recommendService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendPagerNote.title, notes = RecommendPagerNote.notes)
    @PostMapping("/pager")
    public RecommendPagerResponse pager(@Valid @RequestBody RecommendPagerRequest request, BindingResult error){
        return recommendService.selectPager(request);
    }
}
