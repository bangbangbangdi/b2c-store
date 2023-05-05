package com.atguigu.admin.service;

import com.atguigu.param.ProductSaveParam;
import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.service
 * className:      ProductService
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/5 15:11
 * version:    1.0
 */
public interface ProductService {

    /**
     * 全部商品查询和搜索查询的方法
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 进行商品数据保存
     * @param productSaveParam
     * @return
     */
    R save(ProductSaveParam productSaveParam);

    /**
     * 更新商品数据
     * @param product
     * @return
     */
    R update(Product product);

    /**
     * 删除商品
     * @param productId
     * @return
     */
    R remove(Integer productId);
}
