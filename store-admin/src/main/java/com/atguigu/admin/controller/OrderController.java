package com.atguigu.admin.controller;

import com.atguigu.admin.service.OrderService;
import com.atguigu.clients.OrderClient;
import com.atguigu.param.PageParam;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.controller
 * className:      OrderController
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/6 01:11
 * version:    1.0
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    public R list(PageParam pageParam){
        return orderService.list(pageParam);
    }

}
