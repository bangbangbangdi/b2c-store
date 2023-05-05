package com.atguigu.admin.controller;

import com.atguigu.admin.service.UserService;
import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.controller
 * className:      UserController
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 15:48
 * version:    1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public R userList(PageParam pageParam){
        return userService.userList(pageParam);
    }

    @PostMapping("remove")
    public R userRemove(CartListParam cartListParam) {
        return userService.userRemove(cartListParam);
    }

    @PostMapping("update")
    public R userUpdate(User user){
        return userService.userUpdate(user);
    }

    @PostMapping("save")
    public R userSave(User user){
        return userService.userSave(user);
    }

}