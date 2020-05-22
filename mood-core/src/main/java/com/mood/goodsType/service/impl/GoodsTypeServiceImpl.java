package com.mood.goodsType.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.goodsType.GoodsType;
import com.mood.entity.goodsType.request.*;
import com.mood.entity.goodsType.response.*;
import com.mood.entity.typeSpec.TypeSpec;
import com.mood.entity.typeSpec.request.TypeSpecListRequest;
import com.mood.goodsClass.dao.GoodsClassDao;
import com.mood.goodsType.dao.GoodsTypeDao;
import com.mood.goodsType.service.GoodsTypeService;
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
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Autowired
    private GoodsClassDao goodsClassDao;

    @Autowired
    private TypeSpecService typeSpecService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsType selectById(String id){
        return goodsTypeDao.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsTypeInsertResponse insert(GoodsTypeInsertRequest request){
        GoodsType goodsType = OrikaMapper.map(request, GoodsType.class);
        goodsType.setId(IdGen.uuid());
        goodsType.setCreateTime(System.currentTimeMillis());
        goodsType.setUpdateTime(System.currentTimeMillis());
        goodsTypeDao.insert(goodsType);
        typeSpecService.update(goodsType.getId(), request.getSpecIds());
        return OrikaMapper.map(goodsType, GoodsTypeInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsTypeUpdateResponse update(GoodsTypeUpdateRequest request){
        GoodsType goodsType = OrikaMapper.map(request, GoodsType.class);
        goodsTypeDao.update(goodsType);
        typeSpecService.update(goodsType.getId(), request.getSpecIds());
        return OrikaMapper.map(goodsType, GoodsTypeUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsTypeDeleteResponse delete(GoodsTypeDeleteRequest request){
        goodsTypeDao.deleteById(request.getId());
        return new GoodsTypeDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsTypeSelectResponse select(GoodsTypeSelectRequest request){
        GoodsType goodsType = goodsTypeDao.selectById(request.getId());
        GoodsTypeSelectResponse goodsTypeSelectResponse = OrikaMapper.map(goodsType, GoodsTypeSelectResponse.class);
        List<TypeSpec> typeSpecList = typeSpecService.selectByTypeId(request.getId());
        String[] specIds = new String[typeSpecList.size()];
        for(Integer num = 0; num <typeSpecList.size(); num++){
            specIds[num] = typeSpecList.get(num).getSpecId();
        }
        goodsTypeSelectResponse.setSpecIds(specIds);
        return goodsTypeSelectResponse;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsTypeListResponse selectList(GoodsTypeListRequest request){
        GoodsType goodsType = OrikaMapper.map(request, GoodsType.class);
        List<GoodsType> goodsTypeList = goodsTypeDao.selectAll(goodsType);
        List<GoodsTypeDetailsResponse> detailsList = OrikaMapper.mapList(goodsTypeList, GoodsTypeDetailsResponse.class);
        GoodsTypeListResponse response = new GoodsTypeListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsTypePagerResponse selectPager(GoodsTypePagerRequest request){
        GoodsType goodsType = OrikaMapper.map(request, GoodsType.class);
        Pager<GoodsType> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(goodsType);
        pager = goodsTypeDao.selectPager(pager);
        List<GoodsTypeDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), GoodsTypeDetailsResponse.class);
        GoodsTypePagerResponse response = OrikaMapper.map(pager, GoodsTypePagerResponse.class);
        for(GoodsTypeDetailsResponse goodsTypeDetailsResponse: detailsList){
            goodsTypeDetailsResponse.setGoodsClass(goodsClassDao.selectById(goodsTypeDetailsResponse.getClassId()));
        }
        response.setList(detailsList);
        return response;
    }
}
