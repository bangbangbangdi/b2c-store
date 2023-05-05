package com.atguigu.clients;

import com.atguigu.param.PageParam;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.clients
 * className:      OrderClient
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/5 22:45
 * version:    1.0
 */
@FeignClient("order-service")
public interface OrderClient {

    @PostMapping("/order/remove/check")
    R check(@RequestBody Integer productId);

    @PostMapping("/order/admin/list")
    R adminList(@RequestBody PageParam pageParam);
}
