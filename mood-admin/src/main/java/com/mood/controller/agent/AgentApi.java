package com.mood.controller.agent;

import com.mood.annotation.LoginRequired;
import com.mood.base.BaseController;
import com.mood.entity.agent.notes.*;
import com.mood.entity.agent.request.*;
import com.mood.entity.agent.response.*;
import com.mood.agent.service.AgentService;
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
@RequestMapping(value = "/api/v1/agent", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class AgentApi extends BaseController {

    @Autowired
    private AgentService agentService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = AgentInsertNote.title, notes = AgentInsertNote.notes)
    @PostMapping("")
    public AgentInsertResponse create(@Valid @RequestBody AgentInsertRequest request, BindingResult error){
        return agentService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = AgentUpdateNote.title, notes = AgentUpdateNote.notes)
    @PostMapping("update")
    public AgentUpdateResponse update(@Valid @RequestBody AgentUpdateRequest request, BindingResult error){
        return agentService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = AgentDeleteNote.title, notes = AgentDeleteNote.notes)
    @PostMapping("delete")
    public AgentDeleteResponse delete(@Valid @RequestBody AgentDeleteRequest request, BindingResult error){
        return agentService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = AgentSelectNote.title, notes = AgentSelectNote.notes)
    @PostMapping("select")
    public AgentSelectResponse selete(@Valid @RequestBody AgentSelectRequest request, BindingResult error){
        return agentService.select(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = AgentListNote.title, notes = AgentListNote.notes)
    @PostMapping("/list")
    @LoginRequired
    public AgentListResponse list(@Valid @RequestBody AgentListRequest request, BindingResult error){
        return agentService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = AgentPagerNote.title, notes = AgentPagerNote.notes)
    @PostMapping("/pager")
    public AgentPagerResponse pager(@Valid @RequestBody AgentPagerRequest request, BindingResult error){
        return agentService.selectPager(request);
    }


}
