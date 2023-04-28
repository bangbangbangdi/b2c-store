package com.atguigu.user.service;

import com.atguigu.param.UserCheckParam;
import com.atguigu.param.UserLoginParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import org.springframework.stereotype.Service;

@Service
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
}
