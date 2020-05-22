package com.mood.controller.order;

import com.mood.base.BaseController;
import com.mood.entity.order.notes.*;
import com.mood.entity.order.request.*;
import com.mood.entity.order.response.*;
import com.mood.order.service.OrderService;
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
@RequestMapping(value = "/api/v1/order", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class OrderApi extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = OrderInsertNote.title, notes = OrderInsertNote.notes)
    @PostMapping("")
    public OrderInsertResponse create(@Valid @RequestBody OrderInsertRequest request, BindingResult error){
        return orderService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = OrderUpdateNote.title, notes = OrderUpdateNote.notes)
    @PostMapping("update")
    public OrderUpdateResponse update(@Valid @RequestBody OrderUpdateRequest request, BindingResult error){
        return orderService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = OrderDeleteNote.title, notes = OrderDeleteNote.notes)
    @PostMapping("delete")
    public OrderDeleteResponse delete(@Valid @RequestBody OrderDeleteRequest request, BindingResult error){
        return orderService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = OrderSelectNote.title, notes = OrderSelectNote.notes)
    @PostMapping("select")
    public OrderSelectResponse selete(@Valid @RequestBody OrderSelectRequest request, BindingResult error){
        return orderService.select(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = OrderListNote.title, notes = OrderListNote.notes)
    @PostMapping("/list")
    public OrderListResponse list(@Valid @RequestBody OrderListRequest request, BindingResult error){
        return orderService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = OrderPagerNote.title, notes = OrderPagerNote.notes)
    @PostMapping("/pager")
    public OrderPagerResponse pager(@Valid @RequestBody OrderPagerRequest request, BindingResult error){
        return orderService.selectPager(request);
    }

    /**
     * 发货
     * @param request
     * @return
     */
    @ApiOperation(value = OrderUpdateNote.title, notes = OrderUpdateNote.notes)
    @PostMapping("shipping")
    public OrderShippingResponse shipping(@Valid @RequestBody OrderShippingRequest request, BindingResult error){
        return orderService.shipping(request);
    }


}
