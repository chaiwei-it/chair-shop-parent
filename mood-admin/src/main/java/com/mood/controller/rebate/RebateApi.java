package com.mood.controller.rebate;

import com.mood.base.BaseController;
import com.mood.entity.goods.notes.GoodsInsertNote;
import com.mood.model.response.RestfulResponse;
import com.mood.rebate.service.RebateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping(value = "/api/v1/rebate", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class RebateApi extends BaseController {

    @Autowired
    private RebateService rebateService;

    /**
     * 添加
     * @param orderId
     * @return
     */
    @ApiOperation(value = GoodsInsertNote.title, notes = GoodsInsertNote.notes)
    @PostMapping("agentRebate")
    public RestfulResponse create(@RequestParam(value = "orderId", required = false, defaultValue = "") String orderId
                                  ){
        return rebateService.rebateByAgent(orderId);
    }



}
