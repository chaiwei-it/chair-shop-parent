package com.mood.image.service.impl;

import com.mood.base.Pager;
import com.mood.entity.rabate.Image;
import com.mood.image.dao.ImageDao;
import com.mood.image.service.ImageService;
import com.mood.utils.IdGen;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;


    @Override
    public int insert(Image image) {
        image.setId(IdGen.uuid());
        image.setCreateTime(System.currentTimeMillis());
        image.setUpdateTime(System.currentTimeMillis());
        image.setDelStatus(0);
        return imageDao.insert(image);
    }

    @Override
    public int update(Image image) {
        image.setUpdateTime(System.currentTimeMillis());
        return imageDao.update(image);
    }

    @Override
    public int deleteById(String id) {
        return imageDao.deleteById(id);
    }

    @Override
    public Image selectById(String id) {
        return imageDao.selectById(id);
    }

    @Override
    public List<Image> selectAll(JSONObject param) {
        return imageDao.selectAll(param);
    }

    @Override
    public Pager<Image> selectPager(Pager pager){
        return imageDao.selectPager(pager);
    }

}
