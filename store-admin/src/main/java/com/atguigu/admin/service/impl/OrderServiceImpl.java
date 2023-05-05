package com.atguigu.admin.service.impl;

import com.atguigu.admin.service.OrderService;
import com.atguigu.clients.OrderClient;
import com.atguigu.param.PageParam;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.service.impl
 * className:      OrderServiceImpl
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/6 01:18
 * version:    1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClient orderClient;

    /**
     * 查询订单数据
     *
     * @param pageParam
     * @return
     */
    @Override
    public R list(PageParam pageParam) {
        R r = orderClient.adminList(pageParam);
        log.info("OrderServiceImpl.list, pageParam= {}, result:{}",pageParam,r);
        return r;
    }
}
