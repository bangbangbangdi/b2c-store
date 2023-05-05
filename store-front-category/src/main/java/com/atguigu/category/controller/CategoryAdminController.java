package com.atguigu.category.controller;

import com.atguigu.category.service.CategoryService;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.category.controller
 * className:      CategoryAdminController
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 23:21
 * version:    1.0
 */
@RestController
@RequestMapping("category")
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam pageParam){
        return categoryService.listPage(pageParam);
    }

    @PostMapping("admin/save")
    public R adminSave(@RequestBody Category category){
        return categoryService.adminSave(category);
    }

    @PostMapping("admin/remove")
    public R adminRemove(@RequestBody Integer categoryId){
        return categoryService.adminRemove(categoryId);
    }

    @PostMapping("admin/update")
    public R adminUpdate(@RequestBody Category category){
        return categoryService.adminUpdate(category);
    }
}
