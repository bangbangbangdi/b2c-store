package com.atguigu.search.search;

import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.search.search
 * @className: SearchService
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/1 15:21
 * @version: 1.0
 */
public interface SearchService {
    /**
     * 根据关键字和分页进行数据库查询
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * es库商品同步:插入和更新
     * @param product
     * @return
     */
    R save(Product product) throws IOException;

    /**
     * 进行es库的商品删除
     * @param productId
     * @return
     */
    R remove(Integer productId) throws IOException;
}
