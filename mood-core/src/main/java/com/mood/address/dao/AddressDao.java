package com.mood.address.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.address.Address;
import com.mood.entity.test.Test;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface AddressDao extends BaseDao<Address> {

    public List<Address> selectAll(Address t, String... data);

}
