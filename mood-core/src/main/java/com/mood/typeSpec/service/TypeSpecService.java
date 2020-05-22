package com.mood.typeSpec.service;

import com.mood.entity.typeSpec.TypeSpec;
import com.mood.entity.typeSpec.request.*;
import com.mood.entity.typeSpec.response.*;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface TypeSpecService {

    public Boolean insert(TypeSpec typeSpec);

    public Boolean update(String typeId, String[] specIds);

    public void delete(TypeSpecDeleteRequest request);

    public TypeSpecSelectResponse select(TypeSpecSelectRequest request);

    public TypeSpecListResponse selectList(TypeSpecListRequest request);

    public TypeSpecPagerResponse selectPager(TypeSpecPagerRequest request);

    public Boolean deleteByTypeId(String typeId);

    public List<TypeSpec> selectByTypeId(String typeId);
}
