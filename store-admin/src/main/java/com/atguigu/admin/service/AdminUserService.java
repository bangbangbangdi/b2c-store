package com.atguigu.admin.service;

import com.atguigu.admin.param.AdminUserParam;
import com.atguigu.admin.pojo.AdminUser;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.service
 * className:      AdminUserService
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 10:52
 * version:    1.0
 */
public interface AdminUserService {

    /**
     * 登陆业务方法
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);

}
