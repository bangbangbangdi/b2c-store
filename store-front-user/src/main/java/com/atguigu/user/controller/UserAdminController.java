package com.atguigu.user.controller;

import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import com.atguigu.user.service.UserService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.user.controller
 * className:      UserAdminController
 * author:     BangDi
 * description:  后台管理调用的controller
 * date:    2023/5/4 15:28
 * version:    1.0
 */
@RestController
@RequestMapping("user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    // 该请求是通过feign调用,数据都是json方式传输的,因此需要添加@RequestBody进行处理
    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam pageParam){
        return userService.listPage(pageParam);
    }

    @PostMapping("admin/remove")
    public R remove(@RequestBody CartListParam cartListParam){
        return userService.remove(cartListParam.getUserId());
    }

    @PostMapping("admin/update")
    public R update(@RequestBody User user){
        return userService.update(user);
    }

    @PostMapping("admin/save")
    public R save(@RequestBody User user){
        return userService.save(user);
    }

}
