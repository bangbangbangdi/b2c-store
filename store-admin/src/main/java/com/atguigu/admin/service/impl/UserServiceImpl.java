package com.atguigu.admin.service.impl;

import com.atguigu.admin.service.UserService;
import com.atguigu.clients.UserClient;
import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.service.impl
 * className:      UserServiceImpl
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 15:59
 * version:    1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;

    /**
     * 用户的展示业务方法
     *
     * @param pageParam
     * @return
     */
    @Cacheable(value = "list.user",key = "#pageParam.currentPage + '-' + #pageParam.pageSize")
    @Override
    public R userList(PageParam pageParam) {
        R r = userClient.adminListPage(pageParam);
        log.info("UserServiceImpl.userList, pageParam= {}, result:{}",pageParam,r);
        return r;
    }

    /**
     * 删除用户数据
     *
     * @param cartListParam
     * @return
     */
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userRemove(CartListParam cartListParam) {
        R r = userClient.adminRemove(cartListParam);
        log.info("UserServiceImpl.userRemove, userId= {}, result:{}",cartListParam,r);
        return r;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userUpdate(User user) {
        R r = userClient.adminUpdate(user);
        log.info("UserServiceImpl.userUpdate, user= {}, result:{}",user,r);
        return r;
    }

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userSave(User user) {
        R r = userClient.adminSave(user);
        log.info("UserServiceImpl.userSave, user= {}, result:{}",user,r);
        return r;
    }
}