package com.mood.address.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.address.dao.AddressDao;
import com.mood.address.service.AddressService;
import com.mood.base.Pager;
import com.mood.entity.address.Address;
import com.mood.entity.address.Address;
import com.mood.entity.address.request.*;
import com.mood.entity.address.response.*;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;


    @Override
    public int insert(Address address) {
        address.setId(IdGen.uuid());
        updateDefaultStatus(address);
        return addressDao.insert(address);
    }

    @Override
    public int update(Address address) {
        updateDefaultStatus(address);
        return addressDao.update(address);
    }

    public void updateDefaultStatus(Address address){
//        if(address.getDefaultStatus() == 1){
//            JSONObject param = new JSONObject();
//            List<Address> addressList = addressDao.selectAll(param);
//            for (Address oldAddress:addressList) {
//                oldAddress.setDefaultStatus(0);
//                addressDao.update(oldAddress);
//            }
//        }
    }

    @Override
    public int deleteById(String id) {
        return addressDao.deleteById(id);
    }

    @Override
    public Address selectById(String id) {
        return addressDao.selectById(id);
    }

    @Override
    public List<Address> selectAll(JSONObject param) {
        return addressDao.selectAll(param);
    }

    @Override
    public Pager<Address> selectPager(Pager pager){
        return addressDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AddressInsertResponse insert(AddressInsertRequest request){
        Address address = OrikaMapper.map(request, Address.class);
        address.setId(IdGen.uuid());
        addressDao.insert(address);
        return OrikaMapper.map(address, AddressInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AddressUpdateResponse update(AddressUpdateRequest request){
        Address address = OrikaMapper.map(request, Address.class);
        if(address.getDefaultStatus() == 1){
            Address oldAddress = new Address();
            address.setUserId(request.getUserId());
            List<Address> addressList = addressDao.selectAll(oldAddress, "default_status desc");
            for(Address newAddress: addressList){
                if(newAddress.getDefaultStatus() == 1 & !newAddress.getId().equals(address.getId())){
                    newAddress.setDefaultStatus(0);
                    addressDao.update(newAddress);
                } else{
                    break;
                }
            }
        }
        addressDao.update(address);
        return OrikaMapper.map(address, AddressUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AddressDeleteResponse delete(AddressDeleteRequest request){
        addressDao.deleteById(request.getId());
        return new AddressDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AddressSelectResponse select(AddressSelectRequest request){
        Address address = addressDao.selectById(request.getId());
        return OrikaMapper.map(address, AddressSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AddressListResponse selectList(AddressListRequest request){
        Address address = OrikaMapper.map(request, Address.class);
        List<Address> addressList = addressDao.selectAll(address);
        List<AddressDetailsResponse> detailsList = OrikaMapper.mapList(addressList, AddressDetailsResponse.class);
        AddressListResponse response = new AddressListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AddressPagerResponse selectPager(AddressPagerRequest request){
        Address address = OrikaMapper.map(request, Address.class);
        Pager<Address> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(address);
        pager = addressDao.selectPager(pager);
        List<AddressDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), AddressDetailsResponse.class);
        AddressPagerResponse response = OrikaMapper.map(pager, AddressPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DefaultAddressResponse selectDefaultAddress(DefaultAddressRequest request){
        Address address = new Address();
        address.setUserId(request.getUserId());
        List<Address> addressList = addressDao.selectAll(address, "default_status desc");
        if(addressList.size() > 0){
            return OrikaMapper.map(addressList.get(0), DefaultAddressResponse.class);
        }
        return new DefaultAddressResponse();
    }

}
