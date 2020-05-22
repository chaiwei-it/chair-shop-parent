package com.mood.interceptor;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.annotation.LoginRequired;
import com.mood.utils.des.DESUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 登录校验
 *
 * @author C
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * token 最长持续时间
     */
    private static final long EXPIRE = 6 * 60 * 60 * 1000;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        LoginRequired annotation = method.getAnnotation(LoginRequired.class);
        if (annotation != null) {
            String token = httpServletRequest.getHeader("x-access-token");
            if (StringUtils.isNotEmpty(token)) {
                String tokenStr = Optional.ofNullable(DESUtil.decrypt(token)).orElse("");
                String[] temp = tokenStr.split(":");
                if (temp.length != 2) {
                    setFailureResponse(httpServletResponse);
                    return false;
                }
                String userId = temp[0];
                long start = Long.parseLong(temp[1]);
                long ctime = System.currentTimeMillis();
                if (ctime - start > EXPIRE) {
                    setFailureResponse(httpServletResponse);
                    return false;
                }
                httpServletRequest.setAttribute("userId", userId);
                return true;
            }
            setFailureResponse(httpServletResponse);
            return false;
        }
        return true;
    }

    private void setFailureResponse(HttpServletResponse response) throws Exception {
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().print(JSON.toJSONString(new Result<>(HttpStatus.UNAUTHORIZED.value(), "请重新登陆")));
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
          Shift.fatal(StatusCode.USER_NOT_LOGIN);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
