package com.atguigu.admin.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.controller
 * className:      CaptchaController
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 09:51
 * version:    1.0
 */
@Controller
@RequestMapping
public class CaptchaController {

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 自动生成验证码图片,写回
         * 并且:将验证码图片存储到session,key=captcha 默认:4个字母
         */
        CaptchaUtil.out(request, response);
    }

}
