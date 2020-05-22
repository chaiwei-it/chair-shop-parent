//package com.mood.controller.member;
//
//import com.alibaba.fastjson.JSON;
//import com.mood.base.BaseController;
//import com.mood.base.Pager;
//import com.mood.common.HttpCode;
//import com.mood.entity.Member;
//import com.mood.entity.userRebate.UserRebate;
//import com.mood.member.service.MemberService;
//import com.mood.userRebate.service.UserRebateService;
//import net.sf.json.JSONObject;
//import org.apache.commons.codec.binary.Base64;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.http.HttpServletRequest;
//import java.security.AlgorithmParameters;
//import java.security.Key;
//import java.security.Security;
//import java.util.List;
//
///**
// * 模块
// * @author chaiwei
// * @time 2018-05-15 下午10:00
// */
//@RestController
//@RequestMapping("/api/{version}/user")
//public class MemberApi extends BaseController {
//
//    public static boolean initialized = false;
//
//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private UserRebateService userRebateService;
//
//    /**
//     * 添加
//     * @param member
//     * @return
//     */
//    @PostMapping("")
//    public ResponseEntity<ModelMap> create(@ModelAttribute Member member,
//                                           ModelMap modelMap, HttpServletRequest request){
//        Integer result = memberService.insert(member);
//        if(result > 0){
//            return setSuccessModelMap(modelMap,member);
//        }
//        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
//    }
//
//    /**
//     * 修改
//     * @param member
//     * @return
//     */
//    @PutMapping("/{id}")
//    public ResponseEntity<ModelMap> update(@PathVariable(value = "id", required = false) String id,
//                          @ModelAttribute Member member,
//                          ModelMap modelMap, HttpServletRequest request){
//        member.setMemberId(id);
//        Integer result = memberService.update(member);
//        if(result > 0){
//            return setSuccessModelMap(modelMap,member);
//        }
//        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
//    }
//
//    /**
//     * 删除
//     * @param id
//     * @return
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ModelMap> delete(@PathVariable(value = "id", required = false) String id,
//                          ModelMap modelMap, HttpServletRequest request){
//        Integer result = memberService.deleteById(id);
//        if(result > 0){
//            return setSuccessModelMap(modelMap);
//        }
//        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
//    }
//
//    /**
//     * 详情
//     * @param
//     * @return
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<ModelMap> select(
//                             ModelMap modelMap, HttpServletRequest request){
//        String userId = getUserId(request);
//        if(userId == null){
//            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
//        }
//        Member member = memberService.selectById(userId);
//        return setSuccessModelMap(modelMap,member);
//    }
//
//    /**
//     * 列表
//     * @param userName
//     * @return
//     */
//    @GetMapping("")
//    public ResponseEntity<ModelMap> list(@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
//                           ModelMap modelMap, HttpServletRequest request){
//        JSONObject param = new JSONObject();
//        if(!"".equals(userName)){
//            param.put("userName",userName);
//        }
//        List<Member> list = memberService.selectAll(param);
//        return setSuccessModelMap(modelMap,list);
//    }
//
//    /**
//     * 分页
//     * @param pageIndex
//     * @param pageSize
//     * @param userName
//     * @return
//     */
//    @GetMapping("pager")
//    public ResponseEntity<ModelMap> pager(@RequestParam(value = "pageIndex",required = false,  defaultValue = "1") Integer pageIndex,
//                                          @RequestParam(value = "pageSize",required = false,  defaultValue = "20") Integer pageSize,
//                                          @RequestParam(value = "userName", required = false, defaultValue = "") String userName,
//                                          ModelMap modelMap, HttpServletRequest request){
//        Pager<Member> pager = new Pager<Member>(pageIndex, pageSize);
//        JSONObject param = new JSONObject();
//        if("".equals(userName)){
//            param.put("userName",userName);
//        }
//        pager.setParams(param);
//        pager = memberService.selectPager(pager);
//        return setSuccessModelMap(modelMap,pager);
//    }
//
//    @PostMapping("getMobile")
//    public ResponseEntity<ModelMap> getMobile(@RequestParam(value = "encryptedData",required = false,  defaultValue = "") String encryptedData,
//                                              @RequestParam(value = "iv",required = false,  defaultValue = "") String iv,
//                                              @RequestParam(value = "code",required = false,  defaultValue = "") String code,
//                                          ModelMap modelMap, HttpServletRequest request){
//        try {
//            com.alibaba.fastjson.JSONObject sessionKey = AesCbcUtil.getSessionKeyOropenid(code,1);
//            byte[] resultByte  = decrypt(Base64.decodeBase64(encryptedData),
//                    Base64.decodeBase64(sessionKey.getString("session_key")),
//                    Base64.decodeBase64(iv));
//            if(null != resultByte && resultByte.length > 0){
//                com.alibaba.fastjson.JSONObject userInfo = JSON.parseObject(new String(resultByte, "UTF-8"));
////                System.out.println(userInfo);
//                String userId = getUserId(request);
//                UserRebate userRebate = userRebateService.selectByUserId(userId);
//                userRebate.setMobile(userInfo.getString("phoneNumber"));
//                userRebateService.update(userRebate);
////                modelMap.put("phoneNumber",userInfo.getString("phoneNumber"));
//                return setSuccessModelMap(modelMap, userRebate);
//            }else{
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return setSuccessModelMap(modelMap);
//    }
//
//    public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
//        initialize();
//        try {
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
//            Key sKeySpec = new SecretKeySpec(keyByte, "AES");
//
//            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
//            byte[] result = cipher.doFinal(content);
//            return result;
//        }  catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void initialize(){
//        if (initialized) return;
//        Security.addProvider(new BouncyCastleProvider());
//        initialized = true;
//    }
//    //生成iv
//    public static AlgorithmParameters generateIV(byte[] iv) throws Exception{
//        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
//        params.init(new IvParameterSpec(iv));
//        return params;
//    }
//
//    @GetMapping("updateMobile")
//    public ResponseEntity<ModelMap> updateMobile(@RequestParam(value = "mobile",required = false,  defaultValue = "") String mobile,
//                                              @RequestParam(value = "code",required = false,  defaultValue = "") String code,
//                                              ModelMap modelMap, HttpServletRequest request){
//        String userId = getUserId(request);
//        if(userId == null){
//            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
//        }
//        UserRebate userRebate = userRebateService.selectByUserId(userId);
//        if(userRebate == null){
//            return setModelMap(modelMap, HttpCode.LOGIN_NEVER_USER);
//        }
//        userRebate.setMobile(mobile);
//        userRebateService.update(userRebate);
//        return setSuccessModelMap(modelMap, userRebate);
//    }
//
//
//
//}
