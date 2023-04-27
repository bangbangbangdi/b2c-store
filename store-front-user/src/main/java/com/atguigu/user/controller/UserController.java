package com.atguigu.user.controller;

import com.atguigu.param.UserCheckParam;
import com.atguigu.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result){

        boolean b = result.hasErrors();

        if (b){
            //return R.fail("账号为null,不可使用!");
        }
        return null;

    }

}
