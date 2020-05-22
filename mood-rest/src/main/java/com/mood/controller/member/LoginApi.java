package com.mood.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.mood.base.BaseController;
import com.mood.code.service.CodeService;
import com.mood.common.HttpCode;
import com.mood.entity.rabate.Code;
import com.mood.entity.rabate.UserOpen;
import com.mood.entity.userRebate.UserRebate;
import com.mood.userOpen.service.UserOpenService;
import com.mood.userRebate.service.UserRebateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容
 *
 * @author chaiwei
 * @time 2018-05-27 22:30
 */
@RestController
@RequestMapping("/api/{version}")
public class LoginApi extends BaseController {

    @Autowired
    private UserRebateService userRebateService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private UserOpenService userOpenService;

    @PostMapping("/login")
    public ResponseEntity<ModelMap> login(@RequestParam(value = "code", required = false, defaultValue = "") String code,
                                          @RequestParam(value = "codeId", required = false, defaultValue = "") String codeId,
                                          @RequestParam(value = "appId", required = false, defaultValue = "0") Integer appId,
                                                 ModelMap modelMap, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sessionId",session.getId());
        //获取微信用户信息
        JSONObject sessionKey = AesCbcUtil.getSessionKeyOropenid(code, appId);
        session.setAttribute("sessionKey", sessionKey);
        net.sf.json.JSONObject param = new net.sf.json.JSONObject();
        String openid = sessionKey.getString("openid");
        String unionid = sessionKey.getString("unionid");
        //通过unionid查找用户
        param.put("openId", openid);
        List<UserOpen> userOpenList = userOpenService.selectAll(param);
        UserRebate member = new UserRebate();
        if(userOpenList.size() == 0){
            member = userRebateService.register(openid, unionid, appId, codeId);
        }else{
            if(userOpenList.get(0).getUnionId() != null && !"".equals(userOpenList.get(0).getUnionId())){
//                param.put("unionid", userOpenList.get(0).getUnionId());
//                List<UserRebate> userList = userRebateService.selectAll(param);
//                if(userList.size() > 0){
//                    member = userList.get(0);
//                }
                member = userRebateService.selectById(userOpenList.get(0).getUserId());
            }
        }
        if(member.getUsername() == null || "".equals(member.getUsername())){
            map.put("isRegister", false);
            return setSuccessModelMap(modelMap, map);
        }
        session.setAttribute("user", member);
        map.put("isRegister", true);
        Code pCode = codeService.selectById(codeId);
        if(pCode == null || "index".equals(pCode.getGoodsId())){
            map.put("url", "index");
        }else{
            map.put("url", pCode.getGoodsId());
        }
//        map.put("url", "index");
        return setSuccessModelMap(modelMap, map);
    }

    @PostMapping("/register")
    public ResponseEntity<ModelMap> register(@RequestParam(value = "encryptedData", required = false, defaultValue = "") String encryptedData,
                                          @RequestParam(value = "iv", required = false, defaultValue = "") String iv,
                                          @RequestParam(value = "codeId", required = false, defaultValue = "") String codeId,
                                          @RequestParam(value = "appId", required = false, defaultValue = "0") Integer appId,
                                          ModelMap modelMap, HttpServletRequest request) {
        //获取微信用户信息
        HttpSession session = request.getSession();
        JSONObject sessionKey = (JSONObject)session.getAttribute("sessionKey");
        JSONObject userJson = AesCbcUtil.getUserInfo(encryptedData, sessionKey.getString("session_key"), iv);
        net.sf.json.JSONObject param = new net.sf.json.JSONObject();
        String openid = userJson.getString("openId");
        String unionid = userJson.getString("unionId");
        //通过unionid查找用户
        param.put("unionid", unionid);
        List<UserRebate> userList = userRebateService.selectAll(param);
        UserRebate userRebate = new UserRebate();
        if(userList.size() == 1){
            userRebate =  userList.get(0);
            if(userRebate.getUsername() == null || "".equals(userRebate.getUsername())){
                userRebate = userRebateService.creat(userJson, codeId, appId);
            }
            userOpenService.getOpenId(userRebate.getId(), unionid, openid, appId);
        }else{
            userRebate = userRebateService.creat(userJson, codeId, appId);
        }
        session.setAttribute("user", userRebate);
        Map<String,String> map = new HashMap<String,String>();
        map.put("sessionId",session.getId());
        Code code2 = codeService.selectByUserId(userRebate.getId());
        map.put("codeId", code2.getId());
        Code pCode = codeService.selectById(codeId);
        if(pCode == null || "index".equals(pCode.getGoodsId())){
            map.put("url", "index");
        }else{
            map.put("url", pCode.getGoodsId());
        }

        return setSuccessModelMap(modelMap, map, userRebate);
    }

    @PostMapping("/getLogin")
    public ResponseEntity<ModelMap> getLogin(ModelMap modelMap, HttpServletRequest request) {
        //获取微信用户信息
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        UserRebate userRebate = userRebateService.selectById(userId);
        HttpSession session = request.getSession();
        session.setAttribute("user", userRebate);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sessionId",session.getId());
        Code code = codeService.selectByUserId(userRebate.getId());
        map.put("codeId", code.getId());
        map.put("user", userRebate);
        return setSuccessModelMap(modelMap, map, userRebate);
    }



}
