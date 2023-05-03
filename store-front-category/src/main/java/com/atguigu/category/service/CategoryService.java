package com.atguigu.category.service;

import com.atguigu.param.ProductHotParam;
import com.atguigu.utils.R;

public interface CategoryService {

    /**
     * 根据类别名称创建类别对象
     * @param categoryName
     * @return
     */
    R byName(String categoryName);

    /**
     * 根据传入的热门类别名称集合，返回类别对应的id集合
     * @param productHotParam
     * @return
     */
    R hotCategory(ProductHotParam productHotParam);

    /**
     * 查询类别数据，进行返回
     * @return
     */
    R list();
}
