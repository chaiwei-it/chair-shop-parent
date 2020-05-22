package com.mood.controller.test;

import com.mood.base.BaseController;
import com.mood.rebate.service.RebateService;
import com.mood.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/test")
public class TestApi extends BaseController {


    @Autowired
    private TemplateService templateService;

    @Autowired
    private RebateService rebateService;

    @PostMapping("")
    public ResponseEntity<ModelMap> test(@RequestParam(value = "orderId", required = false, defaultValue = "") String orderId,
                                         ModelMap modelMap, HttpServletRequest request){
        rebateService.orderRebate(orderId);
        return setSuccessModelMap(modelMap);
    }


}
