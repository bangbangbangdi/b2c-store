package com.atguigu.order.mapper;

import com.atguigu.pojo.Order;
import com.atguigu.vo.AdminOrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.order.mapper
 * className:      OrderMapper
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/3 14:37
 * version:    1.0
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 查询后台管理的数据方法
     * @param offset
     * @param pageSize
     * @return
     */
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset,@Param("pageSize") int pageSize);
}
