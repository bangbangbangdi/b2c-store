package com.atguigu.product.controller;

import com.atguigu.param.ProductCollectParam;
import com.atguigu.product.service.ProductService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.product.controller
 * @className: ProductCollectController
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/2 16:23
 * @version: 1.0
 */
@RestController
@RequestMapping("product")
public class ProductCollectController {

    @Autowired
    private ProductService productService;

    @RequestMapping("collect/list")
    public R productIds(@RequestBody @Validated ProductCollectParam productCollectParam, BindingResult result){
        if (result.hasErrors()) {
            return R.ok("没有收藏数据");
        }
        return productService.ids(productCollectParam.getProductIds());
    }

}
