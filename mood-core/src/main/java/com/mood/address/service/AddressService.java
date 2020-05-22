package com.mood.address.service;

import com.mood.base.service.BaseService;
import com.mood.entity.address.Address;
import com.mood.entity.address.request.*;
import com.mood.entity.address.response.*;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AddressService extends BaseService<Address> {

    public AddressInsertResponse insert(AddressInsertRequest request);

    public AddressUpdateResponse update(AddressUpdateRequest request);

    public AddressDeleteResponse delete(AddressDeleteRequest request);

    public AddressSelectResponse select(AddressSelectRequest request);

    public AddressListResponse selectList(AddressListRequest request);

    public AddressPagerResponse selectPager(AddressPagerRequest request);

    public DefaultAddressResponse selectDefaultAddress(DefaultAddressRequest request);
}
