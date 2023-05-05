package com.atguigu.admin.service;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.service
 * className:      CategoryService
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 23:35
 * version:    1.0
 */
public interface CategoryService {

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    R pageList(PageParam pageParam);

    /**
     * 进行分类数据添加
     * @param category
     * @return
     */
    R save(Category category);

    /**
     * 根据id删除类别数据
     * @param categoryId
     * @return
     */
    R remove(Integer categoryId);

    /**
     * 修改类别信息
     * @param category
     * @return
     */
    R update(Category category);
}
