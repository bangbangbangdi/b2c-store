package com.atguigu.order.service;

import com.atguigu.param.OrderParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Order;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.order.service
 * className:      OrderService
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/3 14:34
 * version:    1.0
 */
public interface OrderService extends IService<Order> {
    /**
     * 进行订单数据保存业务
     * @param orderParam
     */
    R save(OrderParam orderParam);

    /**
     * 分组查询订单数据
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 检查订单中是否有商品引用
     * @param productId
     * @return
     */
    R check(Integer productId);

    /**
     * 后台管理查询订单数据
     * @param pageParam
     * @return
     */
    R adminList(PageParam pageParam);
}
