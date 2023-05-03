package com.atguigu.order.controller;

import com.atguigu.order.service.OrderService;
import com.atguigu.param.CartListParam;
import com.atguigu.param.OrderParam;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.order.controller
 * className:      OderController
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/3 14:29
 * version:    1.0
 */
@RestController
@RequestMapping("order")
public class OderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("save")
    public R save(@RequestBody OrderParam orderParam){
        return orderService.save(orderParam);
    }

    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam cartListParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常,查询失败");
        }
        return orderService.list(cartListParam.getUserId());
    }

}
