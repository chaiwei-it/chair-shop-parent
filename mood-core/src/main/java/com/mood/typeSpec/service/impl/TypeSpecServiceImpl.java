package com.mood.typeSpec.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.typeSpec.TypeSpec;
import com.mood.entity.typeSpec.request.*;
import com.mood.entity.typeSpec.response.*;
import com.mood.typeSpec.dao.TypeSpecDao;
import com.mood.typeSpec.service.TypeSpecService;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
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
public class TypeSpecServiceImpl implements TypeSpecService {

    @Autowired
    private TypeSpecDao typeSpecDao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insert(TypeSpec typeSpec){
        typeSpec.setId(IdGen.uuid());
        typeSpec.setCreateTime(System.currentTimeMillis());
        typeSpec.setUpdateTime(System.currentTimeMillis());
        typeSpec.setDelStatus(0);
        typeSpecDao.insert(typeSpec);
        return true;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean update(String typeId, String[] specIds){
        deleteByTypeId(typeId);
        for(String specId: specIds){
            TypeSpec typeSpec = new TypeSpec();
            typeSpec.setSpecId(specId);
            typeSpec.setTypeId(typeId);
            insert(typeSpec);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(TypeSpecDeleteRequest request){
        typeSpecDao.deleteById(request.getId());
        Shift.fatal(StatusCode.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TypeSpecSelectResponse select(TypeSpecSelectRequest request){
        TypeSpec typeSpec = typeSpecDao.selectById(request.getId());
        return OrikaMapper.map(typeSpec, TypeSpecSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TypeSpecListResponse selectList(TypeSpecListRequest request){
        TypeSpec typeSpec = OrikaMapper.map(request, TypeSpec.class);
        List<TypeSpec> typeSpecList = typeSpecDao.selectAll(typeSpec);
        List<TypeSpecDetailsResponse> detailsList = OrikaMapper.mapList(typeSpecList, TypeSpecDetailsResponse.class);
        TypeSpecListResponse response = new TypeSpecListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TypeSpecPagerResponse selectPager(TypeSpecPagerRequest request){
        TypeSpec typeSpec = OrikaMapper.map(request, TypeSpec.class);
        Pager<TypeSpec> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(typeSpec);
        pager = typeSpecDao.selectPager(pager);
        List<TypeSpecDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), TypeSpecDetailsResponse.class);
        TypeSpecPagerResponse response = OrikaMapper.map(pager, TypeSpecPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteByTypeId(String typeId){
        List<TypeSpec> typeSpecList = selectByTypeId(typeId);
        for(TypeSpec typeSpec: typeSpecList){
            typeSpecDao.deleteById(typeSpec.getId());
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<TypeSpec> selectByTypeId(String typeId){
        TypeSpec typeSpec = new TypeSpec();
        typeSpec.setTypeId(typeId);
        List<TypeSpec> typeSpecList = typeSpecDao.selectAll(typeSpec);
        return typeSpecList;
    }
}
