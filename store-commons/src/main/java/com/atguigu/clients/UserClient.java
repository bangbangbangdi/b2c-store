package com.atguigu.clients;

import com.atguigu.param.CartListParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.clients
 * className:      UserClient
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 15:44
 * version:    1.0
 */
@FeignClient("user-service")
public interface UserClient {

    @PostMapping("/user/admin/list")
    R adminListPage(@RequestBody PageParam pageParam);

    @PostMapping("/user/admin/remove")
    R adminRemove(@RequestBody CartListParam cartListParam);

    @PostMapping("/user/admin/update")
    R adminUpdate(@RequestBody User user);

    @PostMapping("/user/admin/save")
    R adminSave(@RequestBody User user);

}
