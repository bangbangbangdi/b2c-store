package com.atguigu.clients;

import com.atguigu.param.ProductHotParam;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.clients
 * @className: CategoryClient
 * @author: BangDi
 * @description: 类别调用接口
 * @date: 2023/4/29 23:52
 * @version: 1.0
 */
@FeignClient("category-service")
public interface CategoryClient {

    @GetMapping("/category/promo/{categoryName}")
    R byName(@PathVariable String categoryName);

    @PostMapping("/category/hots")
    R hots(@RequestBody ProductHotParam productHotParam);

    @GetMapping("/category/list")
    R list();

}
