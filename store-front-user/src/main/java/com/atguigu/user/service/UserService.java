package com.atguigu.user.service;

import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.param.UserCheckParam;
import com.atguigu.param.UserLoginParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import org.springframework.stereotype.Service;

public interface UserService {
    R check(UserCheckParam userCheckParam);

    /**
     * @param user:
      * @return R
     * @author BangDi
     * @description 注册业务方法
     * @date 2023/4/28 11:49
     */
    R register(User user);

    /**
     * @param userLoginParam:  已经完成校验，但密码是明文
      * @return R
     * @author BangDi
     * @description 登录业务
     * @date 2023/4/28 13:44
     */
    R login(UserLoginParam userLoginParam);

    /**
     * 后台管理调用,查询全部用户数据
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**
     * 根据用户id删除数据
     * @param userId
     * @return
     */
    R remove(Integer userId);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    R update(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    R save(User user);
}
