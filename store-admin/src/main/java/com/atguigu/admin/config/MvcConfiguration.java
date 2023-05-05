package com.atguigu.admin.config;

import com.atguigu.admin.interceptors.LoginProtectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.config
 * className:      MvcConfiguration
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 15:10
 * version:    1.0
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginProtectInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/index.html","/index","/static/**",
                        "/user/login", "/user/logout",
                        "/api/**", "/css/**", "/images/**",
                        "/js/**", "/lib/**","/captcha");
    }
}
