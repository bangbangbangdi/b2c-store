package com.atguigu.category.service.impl;

import com.atguigu.category.mapper.CategoryMapper;
import com.atguigu.category.service.CategoryService;
import com.atguigu.param.ProductHotParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.category.service.impl
 * @className: CategoryServiceImpl
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/29 23:25
 * @version: 1.0
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据类别名称查询类别对象
     *
     * @param categoryName
     * @return
     */
    @Override
    public R byName(String categoryName) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name",categoryName);

        Category category = categoryMapper.selectOne(categoryQueryWrapper);

        if (category == null){
            log.info("CategoryServiceImpl.byName, categoryName= {}, result:{}",categoryName,"查询不到指定类别");
            return R.fail("类别查询失败！");
        }
        log.info("CategoryServiceImpl.byName, categoryName= {}, result:{}",categoryName,R.ok("类别查询成功",category));
        return R.ok("类别查询成功",category);
    }

    /**
     * 根据传入的热门类别名称集合，返回类别对应的id集合
     *
     * @param productHotParam
     * @return
     */
    @Override
    public R hotCategory(ProductHotParam productHotParam) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name",productHotParam.getCategoryName());
        queryWrapper.select("category_id");
        List<Object> ids = categoryMapper.selectObjs(queryWrapper);
        R ok = R.ok("类别查询成功", ids);
        log.info("CategoryServiceImpl.hotCategory, productHotParam= {}, result:{}",productHotParam,ok);
        return ok;
    }

    /**
     * 查询类别数据，进行返回
     *
     * @return
     */
    @Override
    public R list() {
        List<Category> categories = categoryMapper.selectList(null);
        R ok = R.ok("类别全部数据查询成功", categories);
        log.info("CategoryServiceImpl.list, = {}, result:{}","",ok);
        return ok;
    }
}
