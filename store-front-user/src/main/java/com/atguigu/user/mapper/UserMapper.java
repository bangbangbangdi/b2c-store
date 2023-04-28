package com.atguigu.user.mapper;

import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.user.mapper
 * @className: UserMapper
 * @author: BangDi
 * @description:
 * @date: 2023/4/28 10:57
 * @version: 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
