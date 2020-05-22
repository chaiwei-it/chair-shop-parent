package com.mood.controller.sms;

import com.mood.base.BaseController;
import com.mood.utils.IdUtil;
import com.mood.utils.SmsUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 内容
 *
 * @author chaiwei
 * @time 2018-05-27 22:30
 */
@RestController
@RequestMapping("/api/{version}/sms")
public class SmsApi extends BaseController {


    @GetMapping("/sendCode")
    public ResponseEntity<ModelMap> login(@RequestParam(value = "mobile", required = false, defaultValue = "") String mobile,
                                          ModelMap modelMap, HttpServletRequest request) {
        //获取微信用户信息
        String code = IdUtil.getCode(6);
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        String message = "{\"code\":\"" + code + "\"}";
        SmsUtil.sendSms(mobile, "SMS_139236494", message);
        return setSuccessModelMap(modelMap, code);
    }



}
