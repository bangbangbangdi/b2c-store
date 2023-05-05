package com.atguigu.clients;

import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.clients
 * className:      CartClient
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/5 22:31
 * version:    1.0
 */
@FeignClient("cart-service")
public interface CartClient {

    @PostMapping("cart/remove/check")
    R check(Integer productId);

}
