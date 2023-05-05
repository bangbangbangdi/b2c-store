package com.atguigu.cart.service;

import com.atguigu.param.CartSaveParam;
import com.atguigu.pojo.Cart;
import com.atguigu.utils.R;

import java.util.List;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.cart.service
 * @className: CartService
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/2 21:55
 * @version: 1.0
 */
public interface CartService {
    /**
     * 购物车数据添加方法
     * @param cartSaveParam
     * @return 001 成功,002 已经存在num+1,003库存为0无法添加
     */
    R save(CartSaveParam cartSaveParam);

    /**
     * 返回购物车数据
     * @param userId
     * @return 确保要返回一个数组或者集合
     */
    R list(Integer userId);

    /**
     * 更新购物车业务
     * @param cart
     * @return
     */
    R update(Cart cart);

    /**
     * 删除购物车数据
     * @param cart
     * @return
     */
    R remove(Cart cart);

    /**
     * 清空对应id的购物车项
     * @param cartIds
     */
    void clearIds(List<Integer> cartIds);

    /**
     * 查询购物车项
     * @param productId
     * @return
     */
    R check(Integer productId);
}
