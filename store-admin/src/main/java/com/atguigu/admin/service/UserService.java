package com.atguigu.admin.service;

import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.service
 * className:      UserService
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 15:52
 * version:    1.0
 */
public interface UserService {
    /**
     * 用户的展示业务方法
     * @param pageParam
     * @return
     */
    R userList(PageParam pageParam);

    /**
     * 删除用户数据
     * @param cartListParam
     * @return
     */
    R userRemove(CartListParam cartListParam);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    R userUpdate(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    R userSave(User user);
}