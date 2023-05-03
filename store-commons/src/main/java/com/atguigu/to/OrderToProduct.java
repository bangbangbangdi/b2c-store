package com.atguigu.to;

import lombok.Data;

import java.io.Serializable;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.to
 * className:      OrderToProduct
 * author:     BangDi
 * description:  订单发送商品服务的实体
 * date:    2023/5/3 14:22
 * version:    1.0
 */
@Data
public class OrderToProduct implements Serializable {
    public static final Long serialVersionUID = 1L;

    private Integer productId;
    private Integer num;

}
