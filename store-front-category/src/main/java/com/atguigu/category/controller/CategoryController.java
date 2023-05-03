package com.atguigu.category.controller;

import com.atguigu.category.service.CategoryService;
import com.atguigu.param.ProductHotParam;
import com.atguigu.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.category.controller
 * @className: CategoryController
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/29 23:12
 * @version: 1.0
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R byName(@PathVariable String categoryName){

        if (StringUtils.isEmpty(categoryName)){
            return R.fail("类别名称为null,无法查询类别数据");
        }
        return categoryService.byName(categoryName);
    }

    @PostMapping("/hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){
        if (result.hasErrors()){
            return  R.fail("类别查询失败");
        }
        return categoryService.hotCategory(productHotParam);
    }

    @GetMapping("list")
    public R list(){
        return categoryService.list();
    }

}
