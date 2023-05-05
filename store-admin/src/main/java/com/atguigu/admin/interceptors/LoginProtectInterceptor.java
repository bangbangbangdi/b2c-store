package com.atguigu.admin.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.interceptors
 * className:      LoginProtectInterceptor
 * author:     BangDi
 * description:  检查session中是否有数据userInfo? 有则放行 ,无则跳转到登陆页面
 * date:    2023/5/4 15:04
 * version:    1.0
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userInfo = request.getSession().getAttribute("userInfo");
        // false 拦截 | true 放行
        if (userInfo != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/index.html");
            return false;
        }

    }
}