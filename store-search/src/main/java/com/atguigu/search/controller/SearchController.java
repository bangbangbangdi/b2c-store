package com.atguigu.search.controller;

import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Product;
import com.atguigu.search.search.SearchService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.search.controller
 * @className: SearchController
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/1 15:11
 * @version: 1.0
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("product")
    public R searchProduct(@RequestBody ProductSearchParam productSearchParam){

        return searchService.search(productSearchParam);
    }

    /**
     * 同步调用进行商品插入,覆盖更新
     * @param product
     * @return
     */
    @PostMapping("save")
    public R saveProduct(@RequestBody Product product) throws IOException {
        return searchService.save(product);
    }

    @PostMapping("remove")
    public R removeProduct(@RequestBody Integer productId) throws IOException {
        return searchService.remove(productId);
    }
}
