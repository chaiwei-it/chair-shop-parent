package com.mood.controller.userRebate;

import com.alibaba.fastjson.JSON;
import com.mood.base.BaseController;
import com.mood.base.Pager;
import com.mood.common.HttpCode;
import com.mood.controller.member.AesCbcUtil;
import com.mood.entity.userRebate.UserRebate;
import com.mood.userRebate.service.UserRebateService;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/userRebate")
public class UserRebateApi extends BaseController {

    public static boolean initialized = false;

    @Autowired
    private UserRebateService userRebateService;

    /**
     * 添加
     * @param userRebate
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ModelMap> create(@ModelAttribute UserRebate userRebate,
                                           ModelMap modelMap, HttpServletRequest request){
        JSONObject paramCode = new JSONObject();
        Integer result = userRebateService.insert(userRebate);
        if(result > 0){
            return setSuccessModelMap(modelMap,userRebate);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 修改
     * @param userRebate
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelMap> update(@PathVariable(value = "id", required = false) String id,
                          @ModelAttribute UserRebate userRebate,
                          ModelMap modelMap, HttpServletRequest request){
        userRebate.setId(id);
        Integer result = userRebateService.update(userRebate);
        if(result > 0){
            return setSuccessModelMap(modelMap,userRebate);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ModelMap> delete(@PathVariable(value = "id", required = false) String id,
                          ModelMap modelMap, HttpServletRequest request){
        Integer result = userRebateService.deleteById(id);
        if(result > 0){
            return setSuccessModelMap(modelMap);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 详情
     * @param
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ModelMap> select(
                             ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        UserRebate userRebate = userRebateService.selectById(userId);
        return setSuccessModelMap(modelMap,userRebate);
    }

    /**
     * 详情
     * @param userRebateNum
     * @return
     */
    @GetMapping("getUserRebateId/{userRebateNum}")
    public ResponseEntity<ModelMap> getUserRebateId(@PathVariable(value = "userRebateNum", required = false) String userRebateNum,
                                           ModelMap modelMap, HttpServletRequest request){
        JSONObject param = new JSONObject();
        param.put("userRebateNum",userRebateNum);
        List<UserRebate> list = userRebateService.selectAll(param);
        UserRebate userRebate = new UserRebate();
        if(list.size() > 0){
            userRebate = list.get(0);
        }
        return setSuccessModelMap(modelMap,userRebate);
    }

    /**
     * 列表
     * @param userRebateName
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<ModelMap> list(@RequestParam(value = "userRebateName", required = false, defaultValue = "") String userRebateName,
                                         @RequestParam(value = "categoryId", required = false, defaultValue = "") String categoryId,
                           ModelMap modelMap, HttpServletRequest request){
        JSONObject param = new JSONObject();
        if(!"".equals(userRebateName)){
            param.put("userRebateName",userRebateName);
        }
        if(!"".equals(categoryId)){
            param.put("categoryId",categoryId);
        }
        List<UserRebate> list = userRebateService.selectAll(param);
        return setSuccessModelMap(modelMap,list);
    }

    /**
     * 分页
     * @param pageIndex
     * @param pageSize
     * @param userRebateName
     * @return
     */
    @GetMapping("pager")
    public ResponseEntity<ModelMap> pager(@RequestParam(value = "pageIndex",required = false,  defaultValue = "1") Integer pageIndex,
                                          @RequestParam(value = "pageSize",required = false,  defaultValue = "20") Integer pageSize,
                                          @RequestParam(value = "userRebateName", required = false, defaultValue = "") String userRebateName,
                                          ModelMap modelMap, HttpServletRequest request){
        Pager<UserRebate> pager = new Pager<UserRebate>(pageIndex, pageSize);
        JSONObject param = new JSONObject();
        if("".equals(userRebateName)){
            param.put("userRebateName",userRebateName);
        }
        pager.setParams(param);
        pager = userRebateService.selectPager(pager);
        return setSuccessModelMap(modelMap,pager);
    }

    /**
     * 列表
     * @param userType
     * @return
     */
    @GetMapping("getRebateUserList")
    public ResponseEntity<ModelMap> getRebateUserList(@RequestParam(value = "userType", required = false, defaultValue = "1") Integer userType,
                                         ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        List<UserRebate> returnList = new ArrayList<UserRebate>();
        List<UserRebate> firstList = new ArrayList<UserRebate>();
        if(userType >= 1){
            firstList = userRebateService.selectByParentId(userId);
            returnList = firstList;
        }
        List<UserRebate> secondList = new ArrayList<UserRebate>();
        if(userType >= 2){
            for (UserRebate UserRebate : firstList) {
                List<UserRebate> list = userRebateService.selectByParentId(UserRebate.getId());
                secondList.addAll(list);
            }
            returnList = secondList;
        }
        List<UserRebate> thirdList = new ArrayList<UserRebate>();
        if(userType == 3){
            for (UserRebate UserRebate : secondList) {
                List<UserRebate> list = userRebateService.selectByParentId(UserRebate.getId());
                thirdList.addAll(list);
            }
            returnList = thirdList;
        }
        return setSuccessModelMap(modelMap, returnList);
    }

    /**
     * 列表
     * @param
     * @return
     */
    @GetMapping("getUserNum")
    public ResponseEntity<ModelMap> getUserNum(ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        List<UserRebate> returnList = new ArrayList<UserRebate>();
        List<UserRebate> firstList = new ArrayList<UserRebate>();
        firstList = userRebateService.selectByParentId(userId);
        returnList = firstList;
        List<UserRebate> secondList = new ArrayList<UserRebate>();
        for (UserRebate UserRebate : firstList) {
            List<UserRebate> list = userRebateService.selectByParentId(UserRebate.getId());
            secondList.addAll(list);
        }
        returnList.addAll(secondList);
        List<UserRebate> thirdList = new ArrayList<UserRebate>();
        for (UserRebate UserRebate : secondList) {
            List<UserRebate> list = userRebateService.selectByParentId(UserRebate.getId());
            thirdList.addAll(list);
        }
        returnList.addAll(thirdList);
        Integer totalNum = returnList.size();
        String todayString = getToday();
        String yesterdayString = getYesterday();
        Integer todayNum = 0;
        Integer yesterdayNum = 0;
        for(UserRebate userRebate: returnList){
            if(userRebate.getCreateDate().startsWith(todayString)){
                todayNum++;
            }else if(userRebate.getCreateDate().startsWith(yesterdayString)){
                yesterdayNum++;
            }
        }
        return setSuccessModelMap(modelMap, totalNum, todayNum, yesterdayNum);
    }

    public static String getYesterday(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    public static String getToday() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    @GetMapping("getParentUser")
    public ResponseEntity<ModelMap> getParentUser(ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        UserRebate userRebate =  userRebateService.selectById(userId);
        return setSuccessModelMap(modelMap, userRebateService.selectById(userRebate.getParentId()));
    }

    @PostMapping("getMobile")
    public ResponseEntity<ModelMap> getMobile(@RequestParam(value = "encryptedData",required = false,  defaultValue = "") String encryptedData,
                                              @RequestParam(value = "iv",required = false,  defaultValue = "") String iv,
                                              @RequestParam(value = "code",required = false,  defaultValue = "") String code,
                                          ModelMap modelMap, HttpServletRequest request){
        try {
            com.alibaba.fastjson.JSONObject sessionKey = AesCbcUtil.getSessionKeyOropenid(code,1);
            byte[] resultByte  = decrypt(Base64.decodeBase64(encryptedData),
                    Base64.decodeBase64(sessionKey.getString("session_key")),
                    Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                com.alibaba.fastjson.JSONObject userInfo = JSON.parseObject(new String(resultByte, "UTF-8"));
//                System.out.println(userInfo);
                String userId = getUserId(request);
                UserRebate userRebate = userRebateService.selectById(userId);
                userRebate.setMobile(userInfo.getString("phoneNumber"));
                userRebateService.update(userRebate);
//                modelMap.put("phoneNumber",userInfo.getString("phoneNumber"));
                return setSuccessModelMap(modelMap, userRebate);
            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setSuccessModelMap(modelMap);
    }

    public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");

            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void initialize(){
        if (initialized) return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }
    //生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception{
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }

    @PostMapping("updateMobile")
    public ResponseEntity<ModelMap> updateMobile(@RequestParam(value = "mobile",required = false,  defaultValue = "") String mobile,
                                              @RequestParam(value = "code",required = false,  defaultValue = "") String code,
                                              ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        UserRebate userRebate = userRebateService.selectById(userId);
        if(userRebate == null){
            return setModelMap(modelMap, HttpCode.LOGIN_NEVER_USER);
        }
        userRebate.setMobile(mobile);
        userRebateService.update(userRebate);
        return setSuccessModelMap(modelMap, userRebate);
    }




}
