package com.lq.kexin.controller;

import com.lq.kexin.util.TokenManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginVerificationInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private TokenManager tokenManager;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    int userId = tokenManager.checkToken(token);
                    if (userId == -1) {
                        break;
                    }
                    request.getSession().setAttribute("user_id", userId);
                    return true;
                }
            }
        }
        response.sendRedirect("/kexin/user/login");
        return false;
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
}
