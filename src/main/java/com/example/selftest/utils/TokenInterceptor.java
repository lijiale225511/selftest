package com.example.selftest.utils;

import com.example.selftest.dto.TokenDTO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取验证码，用户登录不拦截与验证
        if (request.getRequestURL().indexOf("/api/login/kaptcha") != -1
                || request.getRequestURL().indexOf("/api/login/check") != -1
                || request.getRequestURL().indexOf("/api/upload/accessory") != -1
                || request.getRequestURL().indexOf("/api/template") != -1) {
            response.setHeader("token_status", "ok");
            return true;
        } else {
            if (request.getHeader("token") == null) {
                response.setHeader("token_status", "no");
                return false;
            } else {
                // 通过客户端传递的Token参数进行验证，注意header中的属性名要小写
                TokenDTO tokenDTO = JWTUtil.verifyToken(request.getHeader("token"));
                if (tokenDTO != null) {
                    response.setHeader("token_status", "ok");
                    return true;
                } else {
                    response.setHeader("token_status", "no");
                    return false;
                }
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
