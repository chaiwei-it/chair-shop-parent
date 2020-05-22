package com.mood.controller.address;

import com.mood.address.service.AddressService;
import com.mood.base.BaseController;
import com.mood.entity.address.notes.*;
import com.mood.entity.address.request.*;
import com.mood.entity.address.response.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/{version}/address")
public class AddressApi extends BaseController {

    @Autowired
    private AddressService addressService;

    /**
     * 添加
     * @param request
     * @return
     */
    @ApiOperation(value = AddressInsertNote.title, notes = AddressInsertNote.notes)
    @PostMapping("")
    public AddressInsertResponse create(@Valid @RequestBody AddressInsertRequest request, BindingResult error,
                                        HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){

        }
        request.setUserId(userId);
        return addressService.insert(request);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @ApiOperation(value = AddressUpdateNote.title, notes = AddressUpdateNote.notes)
    @PostMapping("update")
    public AddressUpdateResponse update(@Valid @RequestBody AddressUpdateRequest request, BindingResult error,
                                        HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){

        }
        request.setUserId(userId);
        return addressService.update(request);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @ApiOperation(value = AddressDeleteNote.title, notes = AddressDeleteNote.notes)
    @PostMapping("delete")
    public AddressDeleteResponse delete(@Valid @RequestBody AddressDeleteRequest request, BindingResult error){
        return addressService.delete(request);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @ApiOperation(value = AddressSelectNote.title, notes = AddressSelectNote.notes)
    @PostMapping("select")
    public AddressSelectResponse selete(@Valid @RequestBody AddressSelectRequest request, BindingResult error){
        return addressService.select(request);
    }
//
    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = AddressListNote.title, notes = AddressListNote.notes)
    @PostMapping("/list")
    public AddressListResponse list(@Valid @RequestBody AddressListRequest request, BindingResult error,
                                    HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){

        }
        request.setUserId(userId);
        return addressService.selectList(request);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ApiOperation(value = AddressPagerNote.title, notes = AddressPagerNote.notes)
    @PostMapping("/pager")
    public AddressPagerResponse pager(@Valid @RequestBody AddressPagerRequest request, BindingResult error){
        return addressService.selectPager(request);
    }

    @ApiOperation(value = AddressListNote.title, notes = AddressListNote.notes)
    @PostMapping("/getDefaultAddress")
    public DefaultAddressResponse getDefaultAddress(@Valid @RequestBody DefaultAddressRequest request, BindingResult error,
                                    HttpServletRequest httpServletRequest){
        String userId = getUserId(httpServletRequest);
        if(userId == null){

        }
        request.setUserId(userId);
        return addressService.selectDefaultAddress(request);
    }


}
