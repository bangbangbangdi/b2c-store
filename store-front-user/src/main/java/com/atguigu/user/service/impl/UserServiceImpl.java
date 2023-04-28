package com.atguigu.user.service.impl;

import com.atguigu.constants.UserConstants;
import com.atguigu.param.UserCheckParam;
import com.atguigu.param.UserLoginParam;
import com.atguigu.pojo.User;
import com.atguigu.user.mapper.UserMapper;
import com.atguigu.user.service.UserService;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @param userCheckParam:
      * @return R
     * @author BangDi
     * @description 查询账号是否可用
     * @date 2023/4/28 10:52
     */
    @Override
    public R check(UserCheckParam userCheckParam) {

        // 参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userCheckParam.getUserName());

        // 数据库查询
        Long total = userMapper.selectCount(queryWrapper);

        // 查询结果处理
        if (total == 0){
            log.info("UserServiceImpl.check., userCheckParam= {}",userCheckParam,"result:{}","账号可以使用");
            return R.ok("账号不存在,可用使用!");
        }
        log.info("UserServiceImpl.check., userCheckParam= {}",userCheckParam,"result:{}","账号不可使用");
        return R.fail("账号已存在，不可注册");
    }

    /**
     * @param user:
      * @return R
     * @author BangDi
     * @description 注册业务
     * @date 2023/4/28 11:52
     */
    @Override
    public R register(User user) {
        // 1.检查账号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());

        Long total = userMapper.selectCount(queryWrapper);

        if (total > 0){
            log.info("UserServiceImpl.register., user= {}",user,"result:{}","账号存在，注册失败");
            return R.fail("账号存在，不可注册！");
        }


        // 2.密码加密处理，注意要加盐
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        // 3.插入数据库数据
        int rows = userMapper.insert(user);

        // 4.返回封装结果
        if (rows == 0){
            log.info("UserServiceImpl.register., user= {}",user,"result:{}","数据插入失败，注册失败");
            return R.fail("注册失败！请稍后重试");
        }

        log.info("UserServiceImpl.register., user= {}",user,"result:{}","注册成功");
        return R.ok("注册成功");
    }

    /**
     * @param userLoginParam:
      * @return R
     * @author BangDi
     * @description 登录业务
     * @date 2023/4/28 13:46
     */
    @Override
    public R login(UserLoginParam userLoginParam) {
        // 1.密码的加密和加盐处理
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);

        // 2.账号和密码进行数据库查询，返回一个完整的数据库user对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userLoginParam.getUserName());
        queryWrapper.eq("password",newPwd);

        User user = userMapper.selectOne(queryWrapper);

        // 3.判断返回结果
        if (user == null){
            log.info("UserServiceImpl.login., userLoginParam= {}",userLoginParam,"result:{}","账号或密码错误");
            return R.fail("账号或密码错误！");
        }

        log.info("UserServiceImpl.login., userLoginParam= {}",userLoginParam,"result:{}","登录成功!");
        user.setPassword(null); // 不给前端返回password
        return R.ok("登录成功",user);
    }
}
