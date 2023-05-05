package com.atguigu.admin.service.impl;

import com.atguigu.admin.service.ProductService;
import com.atguigu.clients.ProductClient;
import com.atguigu.clients.SearchClient;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.service.impl
 * className:      ProductSerivceImpl
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/5 15:13
 * version:    1.0
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductClient productClient;

    /**
     * 全部商品查询和搜索查询的方法
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {
        R r = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search, productSearchParam= {}, result:{}",productSearchParam,r);
        return r;
    }

    /**
     * 进行商品数据保存
     *
     * @param productSaveParam
     * @return
     */
    @Override
    public R save(ProductSaveParam productSaveParam) {
        R r = productClient.adminSave(productSaveParam);
        log.info("ProductServiceImpl.save, productSaveParam= {}, result:{}",productSaveParam,r);
        return r;
    }

    /**
     * 更新商品数据
     *
     * @param product
     * @return
     */
    @Override
    public R update(Product product) {
        R r = productClient.adminUpdate(product);
        log.info("ProductServiceImpl.update, product= {}, result:{}",product,r);
        return r;
    }

    /**
     * 删除商品
     *
     * @param productId
     * @return
     */
    @Override
    public R remove(Integer productId) {
        R r = productClient.adminRemove(productId);
        log.info("ProductServiceImpl.remove, productId= {}, result:{}",productId,r);
        return r;
    }
}
