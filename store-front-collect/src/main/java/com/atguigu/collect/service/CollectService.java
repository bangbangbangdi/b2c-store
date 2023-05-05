package com.atguigu.collect.service;

import com.atguigu.pojo.Collect;
import com.atguigu.utils.R;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.collect.service
 * @className: CollectService
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/2 11:25
 * @version: 1.0
 */
public interface CollectService {

    /**
     * 收藏添加的方法
     * @param collect
     * @return
     */
    R save(Collect collect);

    /**
     * 根据用户id查询商品信息集合
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 根据用户id和商品id删除收藏数据
     * @param collect userId productId
     * @return
     */
    R remove(Collect collect);

    /**
     * 根据商品Id进行删除
     * @param productId
     * @return
     */
    R removeByPid(Integer productId);
}
