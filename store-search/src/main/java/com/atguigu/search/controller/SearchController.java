package com.atguigu.search.controller;

import com.atguigu.param.ProductSearchParam;
import com.atguigu.search.search.SearchService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
