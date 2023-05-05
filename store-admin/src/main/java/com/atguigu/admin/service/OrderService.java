package com.atguigu.admin.service;

import com.atguigu.param.PageParam;
import com.atguigu.utils.R;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.service
 * className:      OrderService
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/6 01:17
 * version:    1.0
 */
public interface OrderService {

    /**
     * 查询订单数据
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);
}