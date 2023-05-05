package com.atguigu.user.service.impl;

import com.atguigu.constants.UserConstants;
import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.param.UserCheckParam;
import com.atguigu.param.UserLoginParam;
import com.atguigu.pojo.User;
import com.atguigu.user.mapper.UserMapper;
import com.atguigu.user.service.UserService;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

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
        queryWrapper.eq("user_name", userCheckParam.getUserName());

        // 数据库查询
        Long total = userMapper.selectCount(queryWrapper);

        // 查询结果处理
        if (total == 0) {
            log.info("UserServiceImpl.check., userCheckParam= {}", userCheckParam, "result:{}", "账号可以使用");
            return R.ok("账号不存在,可用使用!");
        }
        log.info("UserServiceImpl.check., userCheckParam= {}", userCheckParam, "result:{}", "账号不可使用");
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
        queryWrapper.eq("user_name", user.getUserName());

        Long total = userMapper.selectCount(queryWrapper);

        if (total > 0) {
            log.info("UserServiceImpl.register., user= {}", user, "result:{}", "账号存在，注册失败");
            return R.fail("账号存在，不可注册！");
        }


        // 2.密码加密处理，注意要加盐
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        // 3.插入数据库数据
        int rows = userMapper.insert(user);

        // 4.返回封装结果
        if (rows == 0) {
            log.info("UserServiceImpl.register., user= {}", user, "result:{}", "数据插入失败，注册失败");
            return R.fail("注册失败！请稍后重试");
        }

        log.info("UserServiceImpl.register., user= {}", user, "result:{}", "注册成功");
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
        queryWrapper.eq("user_name", userLoginParam.getUserName());
        queryWrapper.eq("password", newPwd);

        User user = userMapper.selectOne(queryWrapper);

        // 3.判断返回结果
        if (user == null) {
            log.info("UserServiceImpl.login., userLoginParam= {}", userLoginParam, "result:{}", "账号或密码错误");
            return R.fail("账号或密码错误！");
        }

        log.info("UserServiceImpl.login., userLoginParam= {}", userLoginParam, "result:{}", "登录成功!");
        user.setPassword(null); // 不给前端返回password
        return R.ok("登录成功", user);
    }

    /**
     * 后台管理调用,查询全部用户数据
     *
     * @param pageParam
     * @return
     */
    @Override
    public R listPage(PageParam pageParam) {
        IPage<User> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        page = userMapper.selectPage(page, null);

        List<User> records = page.getRecords();
        long total = page.getTotal();
        return R.ok("用户管理查询成功", records, total);
    }

    /**
     * 根据用户id删除数据
     *
     * @param userId
     * @return
     */
    @Override
    public R remove(Integer userId) {

        int rows = userMapper.deleteById(userId);
        log.info("UserServiceImpl.remove, userId= {}, result:{}", userId, rows);
        return R.ok("用户数据删除成功!");
    }

    /**
     * 修改用户信息
     * 1.账号,密码不可修改
     * 2.密码需要进行数据库判断,是不是原来的密码;如果是则不进行修改,如果是新密码,加密后更新
     * 3.修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public R update(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 此处user_id前端做了限制,一定是存在的
        queryWrapper.eq("user_id", user.getUserId());
        queryWrapper.eq("PASSWORD", user.getPassword());
        Long cnt = userMapper.selectCount(queryWrapper);
        if (cnt == 0) {
            // 说明用户确实修改了密码,要对新密码进行加密
            user.setPassword(MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT));
        }
        int i = userMapper.updateById(user);
        log.info("UserServiceImpl.update, user= {}, result:{}", user, i);
        return R.ok("用户信息修改成功");
    }

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    @Override
    public R save(User user) {
        // 1.检查账号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());

        Long total = userMapper.selectCount(queryWrapper);

        if (total > 0) {
            log.info("UserServiceImpl.register., user= {},result:{}", user,"账号存在，添加失败");
            return R.fail("账号存在，不可添加！");
        }


        // 2.密码加密处理，注意要加盐
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        // 3.插入数据库数据
        int rows = userMapper.insert(user);

        // 4.返回封装结果
        if (rows == 0) {
            log.info("UserServiceImpl.save, user= {}, result:{}",user,"添加失败！请稍后重试");
            return R.fail("添加失败！请稍后重试");
        }

        log.info("UserServiceImpl.save, user= {}, result:{}",user,"添加成功");
        return R.ok("添加成功");
    }
}