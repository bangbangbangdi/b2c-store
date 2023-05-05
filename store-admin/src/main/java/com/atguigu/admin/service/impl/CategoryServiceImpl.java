package com.atguigu.admin.service.impl;

import com.atguigu.admin.service.CategoryService;
import com.atguigu.clients.CategoryClient;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.service.impl
 * className:      CategoryServiceImpl
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 23:36
 * version:    1.0
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryClient categoryClient;

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    @Cacheable(value = "list.category", key = "#pageParam.currentPage + '-' + #pageParam.pageSize")
    @Override
    public R pageList(PageParam pageParam) {
        R r = categoryClient.adminPageList(pageParam);
        log.info("CategoryServiceImpl.pageList, pageParam= {}, result:{}", pageParam, r);
        return r;
    }

    /**
     * 进行分类数据添加
     *
     * @param category
     * @return
     */
    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R save(Category category) {
        R r = categoryClient.adminSave(category);
        log.info("CategoryServiceImpl.save, category= {}, result:{}",category,r);
        return r;
    }

    /**
     * 根据id删除类别数据
     *
     * @param categoryId
     * @return
     */
    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R remove(Integer categoryId) {
        R r = categoryClient.adminRemove(categoryId);
        log.info("CategoryServiceImpl.remove, categoryId= {}, result:{}",categoryId,r);
        return r;
    }

    /**
     * 修改类别信息
     *
     * @param category
     * @return
     */
    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R update(Category category) {
        R r = categoryClient.adminUpdate(category);
        log.info("CategoryServiceImpl.update, category= {}, result:{}",category,r);
        return r;
    }
}