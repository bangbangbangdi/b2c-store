package com.atguigu.product.service;

import com.atguigu.param.ProductHotParam;
import com.atguigu.param.ProductIdsParam;
import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;

import java.util.List;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.product.service
 * @className: ProductService
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/30 00:06
 * @version: 1.0
 */
public interface ProductService {
    /**
     * 根据单类别名称，查询热门商品，至多7条数据
     * @param categoryName 类别名称
     * @return
     */
    R promo(String categoryName);

    /**
     * 多类别热门商品查询 根据类别名称查询,至多查询七条
     * @param productHotParam
     * @return
     */
    R hots(ProductHotParam productHotParam);

    /**
     * 查询类别商品集合
     * @return
     */
    R clist();

    /**
     * 通用性的业务
     *  1.传入了类别id，则根据id查询并分页
     *  2.没有传入类别id，则查询全部
     * @param productIdsParam
     * @return
     */
    R byCategory(ProductIdsParam productIdsParam);

    /**
     * 根据商品id查询商品详情信息
     * @param productID
     * @return
     */
    R detail(Integer productID);

    /**
     * 查询商品对应的图片详情
     * @param productID
     * @return
     */
    R pictures(Integer productID);

    /**
     * 搜索服务调用,获取全部商品数据!
     * @return
     */
    List<Product> allList();

    /**
     * 搜索业务需要调用搜索服务
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 根据商品id集合查询商品数据
     * @param productIds
     * @return
     */
    R ids(List<Integer> productIds);

    /**
     * 根据商品Id，查询商品ID集合
     * @param productIds
     * @return
     */
    List<Product> cartList(List<Integer> productIds);
}
