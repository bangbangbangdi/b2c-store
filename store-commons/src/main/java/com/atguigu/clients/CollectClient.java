package com.atguigu.clients;

import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.clients
 * className:      CollectClient
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/5 23:00
 * version:    1.0
 */
@FeignClient("collect-service")
public interface CollectClient {

    @PostMapping("/collect/remove/product")
    R remove(@RequestBody Integer productId);

}
