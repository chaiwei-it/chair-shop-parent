package com.mood.controller.order;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.BaseController;
import com.mood.entity.order.notes.*;
import com.mood.entity.order.request.*;
import com.mood.entity.order.response.*;
import com.mood.model.response.RestfulResponse;
import com.mood.order.service.OrderService;
import com.mood.template.service.TemplateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public OrderInsertResponse create(@Valid @RequestBody OrderInsertRequest request, BindingResult error,
                                      HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        request.setBuyerId(userId);
        return orderService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = OrderUpdateNote.title, notes = OrderUpdateNote.notes)
    @PostMapping("update")
    public OrderUpdateResponse update(@Valid @RequestBody OrderUpdateRequest request, BindingResult error,
                                      HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return orderService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = OrderDeleteNote.title, notes = OrderDeleteNote.notes)
    @PostMapping("delete")
    public OrderDeleteResponse delete(@Valid @RequestBody OrderDeleteRequest request, BindingResult error,
                                      HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return orderService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = OrderSelectNote.title, notes = OrderSelectNote.notes)
    @PostMapping("select")
    public OrderSelectResponse selete(@Valid @RequestBody OrderSelectRequest request, BindingResult error,
                                      HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return orderService.select(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = OrderListNote.title, notes = OrderListNote.notes)
    @PostMapping("/list")
    public OrderListResponse list(@Valid @RequestBody OrderListRequest request, BindingResult error,
                                  HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        request.setBuyerId(userId);
        return orderService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = OrderPagerNote.title, notes = OrderPagerNote.notes)
    @PostMapping("/pager")
    public OrderPagerResponse pager(@Valid @RequestBody OrderPagerRequest request, BindingResult error,
                                    HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return orderService.selectPager(request);
    }

    /**
     * 取消
     * @param request
     * @return
     */
    @ApiOperation(value = OrderAddressNote.title, notes = OrderAddressNote.notes)
    @PostMapping("cancel")
    public RestfulResponse cancel(@Valid @RequestBody OrderDeleteRequest request, BindingResult error,
                                  HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return orderService.cancel(request.getId());
    }

    /**
     * 确认收货
     * @param request
     * @return
     */
    @ApiOperation(value = OrderAddressNote.title, notes = OrderAddressNote.notes)
    @PostMapping("finish")
    public RestfulResponse confirm(@Valid @RequestBody OrderDeleteRequest request, BindingResult error,
                                   HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return orderService.confirm(request.getId());
    }

    /**
     * 获取订单数量
     * @param
     * @return
     */
    @ApiOperation(value = OrderAddressNote.title, notes = OrderAddressNote.notes)
    @PostMapping("getOrderNum")
    public OrderNumResponse getOrderNum(@Valid @RequestBody OrderNumRequest request,BindingResult error,
                                        HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return orderService.getOrderNum(userId);
    }


}
