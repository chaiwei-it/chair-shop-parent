package com.mood.spec.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.spec.Spec;
import com.mood.entity.spec.request.*;
import com.mood.entity.spec.response.*;
import com.mood.entity.specValue.SpecValue;
import com.mood.entity.specValue.response.SpecValueDetailsResponse;
import com.mood.entity.typeSpec.TypeSpec;
import com.mood.spec.dao.SpecDao;
import com.mood.spec.service.SpecService;
import com.mood.specValue.service.SpecValueService;
import com.mood.typeSpec.service.TypeSpecService;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecDao specDao;

    @Autowired
    private TypeSpecService typeSpecService;

    @Autowired
    private SpecValueService specValueService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecInsertResponse insert(SpecInsertRequest request){
        Spec spec = OrikaMapper.map(request, Spec.class);
        spec.setId(IdGen.uuid());
        spec.setCreateTime(System.currentTimeMillis());
        specDao.insert(spec);
        return OrikaMapper.map(spec, SpecInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecUpdateResponse update(SpecUpdateRequest request){
        Spec spec = OrikaMapper.map(request, Spec.class);
        specDao.update(spec);
        return OrikaMapper.map(spec, SpecUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecDeleteResponse delete(SpecDeleteRequest request){
        specDao.deleteById(request.getId());
        return new SpecDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecSelectResponse select(SpecSelectRequest request){
        Spec spec = specDao.selectById(request.getId());
        return OrikaMapper.map(spec, SpecSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecListResponse selectList(SpecListRequest request){
        Spec spec = OrikaMapper.map(request, Spec.class);
        List<Spec> specList = specDao.selectAll(spec);
        List<SpecDetailsResponse> detailsList = OrikaMapper.mapList(specList, SpecDetailsResponse.class);
        SpecListResponse response = new SpecListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecValueDatailsListResponse selectList(SpecValueDatailsListRequest request){

        List<TypeSpec> typeSpecList = typeSpecService.selectByTypeId(request.getTypeId());
        List<SpecDetailsResponse>  specDetailsResponseList = new ArrayList<SpecDetailsResponse>();
        for(TypeSpec typeSpec: typeSpecList){
            Spec spec = specDao.selectById(typeSpec.getSpecId());
            SpecDetailsResponse specDetailsResponse = OrikaMapper.map(spec, SpecDetailsResponse.class);
            List<SpecValueDetailsResponse> specValueList = specValueService.selectBySpecId(typeSpec.getSpecId());
            specDetailsResponse.setValue(specValueList);
            specDetailsResponseList.add(specDetailsResponse);
        }
        SpecValueDatailsListResponse response = new SpecValueDatailsListResponse();
        response.setList(specDetailsResponseList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecPagerResponse selectPager(SpecPagerRequest request){
        Spec spec = OrikaMapper.map(request, Spec.class);
        Pager<Spec> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(spec);
        pager = specDao.selectPager(pager);
        List<SpecDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), SpecDetailsResponse.class);
        SpecPagerResponse response = OrikaMapper.map(pager, SpecPagerResponse.class);
        response.setList(detailsList);
        return response;
    }
}
