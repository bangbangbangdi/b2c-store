package com.atguigu.user.service;

import com.atguigu.pojo.Address;
import com.atguigu.utils.R;

public interface AddressService {
    /**
     * 根据用户id查询 地址数据
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 插入地址数据,插入成功以后，要返回新的数据集合
     * @param address
     * @return
     */
    R save(Address address);

    /**
     * 根据ID删除用户地址数据
     * @param id
     * @return
     */
    R remove(Integer id);
}
