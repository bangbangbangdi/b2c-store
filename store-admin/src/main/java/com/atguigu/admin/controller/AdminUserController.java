package com.atguigu.admin.controller;

import com.atguigu.admin.param.AdminUserParam;
import com.atguigu.admin.pojo.AdminUser;
import com.atguigu.admin.service.AdminUserService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.controller
 * className:      AdminUserController
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 10:09
 * version:    1.0
 */
@RestController
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/user/login")
    public R login( @Validated AdminUserParam adminUserParam, BindingResult result, HttpSession session){
        if (result.hasErrors()) {
            return R.fail("核心参数为null,登陆失败!");
        }

        // 验证码校验
        String captcha = session.getAttribute("captcha").toString();
        if (!adminUserParam.getVerCode().equalsIgnoreCase(captcha)) {
            return R.fail("验证码错误");
        }

        AdminUser user = adminUserService.login(adminUserParam);
        if (user == null){
            return R.fail("账号或者密码错误!");
        }
        session.setAttribute("userInfo",user);
        return R.ok("登陆成功");
    }

    @GetMapping("user/logout")
    public R logout(HttpSession session){
        // 清除session即可
        session.invalidate();

        return R.ok("退出登陆成功");
    }

}
