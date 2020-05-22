package com.mood.controller.cart;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.BaseController;
import com.mood.cart.service.CartService;
import com.mood.common.HttpCode;
import com.mood.entity.cart.notes.*;
import com.mood.entity.cart.request.*;
import com.mood.entity.cart.response.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/cart")
public class CartApi extends BaseController {

    @Autowired
    private CartService cartService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = CartInsertNote.title, notes = CartInsertNote.notes)
    @PostMapping("")
    public CartInsertResponse create(@Valid @RequestBody CartInsertRequest request, BindingResult error,
                                     HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        request.setUserId(userId);
        return cartService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = CartUpdateNote.title, notes = CartUpdateNote.notes)
    @PostMapping("update")
    public CartUpdateResponse update(@Valid @RequestBody CartUpdateRequest request, BindingResult error,
                                     HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return cartService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = CartDeleteNote.title, notes = CartDeleteNote.notes)
    @PostMapping("delete")
    public CartDeleteResponse delete(@Valid @RequestBody CartDeleteRequest request, BindingResult error,
                                     HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return cartService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = CartSelectNote.title, notes = CartSelectNote.notes)
    @PostMapping("select")
    public CartSelectResponse selete(@Valid @RequestBody CartSelectRequest request, BindingResult error,
                                     HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return cartService.select(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = CartListNote.title, notes = CartListNote.notes)
    @PostMapping("/list")
    public CartListResponse list(@Valid @RequestBody CartListRequest request, BindingResult error,
                                 HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        request.setUserId(userId);
        return cartService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = CartPagerNote.title, notes = CartPagerNote.notes)
    @PostMapping("/pager")
    public CartPagerResponse pager(@Valid @RequestBody CartPagerRequest request, BindingResult error,
                                   HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return cartService.selectPager(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = CartListNote.title, notes = CartListNote.notes)
    @PostMapping("/cartNum")
    public CartNumResponse cartNum(@Valid @RequestBody CartListRequest request, BindingResult error,
                                 HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        request.setUserId(userId);
        return cartService.selectCartNum(request);
    }

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = CartListNote.title, notes = CartListNote.notes)
    @PostMapping("/byIds")
    public CartGoodsListResponse byIds(@Valid @RequestBody CartGoodsListRequest request, BindingResult error,
                                 HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){
            Shift.fatal(StatusCode.USER_NOT_LOGIN);
        }
        return cartService.selectGoodsList(request);
    }
}
