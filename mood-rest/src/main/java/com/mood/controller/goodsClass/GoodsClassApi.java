package com.mood.controller.goodsClass;

import com.mood.base.BaseController;
import com.mood.entity.goods.notes.GoodsListNote;
import com.mood.entity.goods.request.IndexGoodsListRequest;
import com.mood.entity.goods.response.IndexGoodsListResponse;
import com.mood.entity.goodsClass.notes.GoodsClassListNote;
import com.mood.entity.goodsClass.request.GoodsClassListRequest;
import com.mood.entity.goodsClass.response.GoodsClassListResponse;
import com.mood.goods.service.GoodsService;
import com.mood.goodsClass.service.GoodsClassService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/goodsClass")
public class GoodsClassApi extends BaseController {

    @Autowired
    private GoodsClassService goodsClassService;

    /**
     * 列表
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsClassListNote.title, notes = GoodsClassListNote.notes)
    @PostMapping("list")
    public GoodsClassListResponse list(@Valid @RequestBody GoodsClassListRequest request, BindingResult error){
        request.setShowStatus(1);
        return goodsClassService.selectList(request);
    }

}
