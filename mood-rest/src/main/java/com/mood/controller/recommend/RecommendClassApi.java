package com.mood.controller.recommend;


import com.mood.base.BaseController;
import com.mood.entity.recommend.notes.*;
import com.mood.entity.recommend.request.*;
import com.mood.entity.recommend.response.*;
import com.mood.recommendClass.service.RecommendClassService;
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
@RequestMapping("/api/{version}/recommendClass")
public class RecommendClassApi extends BaseController {

    @Autowired
    private RecommendClassService recommendClassService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendClassInsertNote.title, notes = RecommendClassInsertNote.notes)
    @PostMapping("")
    public RecommendClassInsertResponse create(@Valid @RequestBody RecommendClassInsertRequest request, BindingResult error){
        return recommendClassService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendClassUpdateNote.title, notes = RecommendClassUpdateNote.notes)
    @PutMapping("")
    public RecommendClassUpdateResponse update(@Valid @RequestBody RecommendClassUpdateRequest request, BindingResult error){
        return recommendClassService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendClassDeleteNote.title, notes = RecommendClassDeleteNote.notes)
    @DeleteMapping("")
    public void delete(@Valid @RequestBody RecommendClassDeleteRequest request, BindingResult error){
        recommendClassService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendClassSelectNote.title, notes = RecommendClassSelectNote.notes)
    @PostMapping("select")
    public RecommendClassSelectResponse selete(@Valid @RequestBody RecommendClassSelectRequest request, BindingResult error){
        return recommendClassService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendClassListNote.title, notes = RecommendClassListNote.notes)
    @PostMapping("/list")
    public RecommendClassListResponse list(@Valid @RequestBody RecommendClassListRequest request, BindingResult error){
        return recommendClassService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = RecommendClassPagerNote.title, notes = RecommendClassPagerNote.notes)
    @PostMapping("/pager")
    public RecommendClassPagerResponse pager(@Valid @RequestBody RecommendClassPagerRequest request, BindingResult error){
        return recommendClassService.selectPager(request);
    }
}
