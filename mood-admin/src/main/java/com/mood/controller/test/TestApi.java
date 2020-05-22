package com.mood.controller.test;

import com.mood.base.BaseController;
import com.mood.entity.test.notes.TestDeleteNote;
import com.mood.entity.test.notes.TestInsertNote;
import com.mood.entity.test.notes.TestListNote;
import com.mood.entity.test.notes.TestPagerNote;
import com.mood.entity.test.notes.TestSelectNote;
import com.mood.entity.test.notes.TestUpdateNote;
import com.mood.entity.test.request.TestDeleteRequest;
import com.mood.entity.test.request.TestInsertRequest;
import com.mood.entity.test.request.TestListRequest;
import com.mood.entity.test.request.TestPagerRequest;
import com.mood.entity.test.request.TestSelectRequest;
import com.mood.entity.test.request.TestUpdateRequest;
import com.mood.entity.test.response.TestInsertResponse;
import com.mood.entity.test.response.TestListResponse;
import com.mood.entity.test.response.TestPagerResponse;
import com.mood.entity.test.response.TestSelectResponse;
import com.mood.entity.test.response.TestUpdateResponse;
import com.mood.test.service.TestService;
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
@RequestMapping(value = "/api/v1/test", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class TestApi extends BaseController {

    @Autowired
    private TestService testService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = TestInsertNote.title, notes = TestInsertNote.notes)
    @PostMapping("")
    public TestInsertResponse create(@Valid @RequestBody TestInsertRequest request, BindingResult error){
        return testService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = TestUpdateNote.title, notes = TestUpdateNote.notes)
    @PutMapping("")
    public TestUpdateResponse update(@Valid @RequestBody TestUpdateRequest request, BindingResult error){
        return testService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = TestDeleteNote.title, notes = TestDeleteNote.notes)
    @DeleteMapping("")
    public void delete(@Valid @RequestBody TestDeleteRequest request, BindingResult error){
        testService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = TestSelectNote.title, notes = TestSelectNote.notes)
    @PostMapping("select")
    public TestSelectResponse selete(@Valid @RequestBody TestSelectRequest request, BindingResult error){
        return testService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = TestListNote.title, notes = TestListNote.notes)
    @PostMapping("/list")
    public TestListResponse list(@Valid @RequestBody TestListRequest request, BindingResult error){
        return testService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = TestPagerNote.title, notes = TestPagerNote.notes)
    @PostMapping("/pager")
    public TestPagerResponse pager(@Valid @RequestBody TestPagerRequest request, BindingResult error){
        return testService.selectPager(request);
    }


}
