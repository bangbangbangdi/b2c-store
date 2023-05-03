package com.atguigu.product.service.impl;

import com.atguigu.clients.CategoryClient;
import com.atguigu.clients.SearchClient;
import com.atguigu.param.ProductHotParam;
import com.atguigu.param.ProductIdsParam;
import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Category;
import com.atguigu.pojo.Picture;
import com.atguigu.pojo.Product;
import com.atguigu.product.mapper.PictureMapper;
import com.atguigu.product.mapper.ProductMapper;
import com.atguigu.product.service.ProductService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.product.service.impl
 * @className: ProductServiceImpl
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/30 00:09
 * @version: 1.0
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private SearchClient searchClient;


    /**
     * 根据单类别名称，查询热门商品，至多7条数据
     * 此处的cacheManager 来自于CacheConfiguration
     *
     * @param categoryName 类别名称
     * @return
     */
    @Cacheable(value = "list.product", key = "#categoryName", cacheManager = "cacheManagerDay")
    @Override
    public R promo(String categoryName) {
        //1.根据类别名称 调用feign客户端访问类别服务获取类别的数据
        R r = categoryClient.byName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo, categoryName= {}, result:{}", categoryName, "类别查询失败");
            return r;
        }
        //2.成功 继续根据类别id查询商品数据[热门 销量倒序 查询7]
        // data = category -- feign {json} LinkedHashMap -- product服务
        LinkedHashMap<String, Object> map = (LinkedHashMap) r.getData();
        Integer categoryId = (Integer) map.get("category_id");

        //3.结果封装即可
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);

        List<Product> productList = page.getRecords();
        log.info("ProductServiceImpl.promo, categoryName= {}, result:{}", categoryName, productList);
        return R.ok("数据查询成功", productList);
    }

    /**
     * 多类别热门商品查询 根据类别名称查询,至多查询七条
     *
     * @param productHotParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productHotParam.categoryName")
    @Override
    public R hots(ProductHotParam productHotParam) {
        R r = categoryClient.hots(productHotParam);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.hots, productHotParam= {}, result:{}", productHotParam, r);
            return r;
        }
        List<Object> ids = (List<Object>) r.getData();

        // 封装查询条件
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", ids);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);

        List<Product> records = page.getRecords();
        R ok = R.ok("多类别热门商品查询成功", records);
        log.info("ProductServiceImpl.hots, productHotParam= {}, result:{}", productHotParam, ok);

        return ok;
    }

    /**
     * 查询类别商品集合
     *
     * @return
     */
    @Override
    public R clist() {
        R r = categoryClient.list();
        log.info("ProductServiceImpl.clist, = {}, result:{}", "", r);
        return r;
    }

    /**
     * 通用性的业务
     * 1.传入了类别id，则根据id查询并分页
     * 2.没有传入类别id，则查询全部
     *
     * @param productIdsParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productIdsParam.categoryID + '-' + #productIdsParam.currentPage + '-' + #productIdsParam.pageSize")
    @Override
    public R byCategory(ProductIdsParam productIdsParam) {
        List<Integer> categoryId = productIdsParam.getCategoryID();
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (!categoryId.isEmpty()) {
            queryWrapper.in("category_id", categoryId);
        }
        IPage<Product> page = new Page<>(productIdsParam.getCurrentPage(), productIdsParam.getPageSize());
        page = productMapper.selectPage(page, queryWrapper);
        R ok = R.ok("查询成功", page.getRecords(), page.getTotal());
        log.info("ProductServiceImpl.byCategory, productIdsParam= {}, result:{}", productIdsParam, ok);
        return ok;
    }

    /**
     * 根据商品id查询商品详情信息
     *
     * @param productID
     * @return
     */
    @Cacheable(value = "product", key = "#productID")
    @Override
    public R detail(Integer productID) {
        Product product = productMapper.selectById(productID);
        R ok = R.ok(product);
        log.info("ProductServiceImpl.detail, productID= {}, result:{}", productID, ok);
        return ok;
    }

    /**
     * 查询商品对应的图片详情
     *
     * @param productID
     * @return
     */
    @Cacheable(value = "pictures", key = "#productID")
    @Override
    public R pictures(Integer productID) {
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productID);
        List<Picture> pictures = pictureMapper.selectList(queryWrapper);
        R ok = R.ok(pictures);
        log.info("ProductServiceImpl.pictures, productID= {}, result:{}", productID, ok);
        return ok;
    }

    /**
     * 搜索服务调用,获取全部商品数据!
     *
     * @return
     */
    @Cacheable(value = "list.category", key = "#root.methodName", cacheManager = "cacheManagerHour")
    @Override
    public List<Product> allList() {
        List<Product> products = productMapper.selectList(null);
        log.info("ProductServiceImpl.allList, = {}, result:{}", "", products.size());
        return products;
    }

    /**
     * 搜索业务需要调用搜索服务
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {
        R r = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search, productSearchParam= {}, result:{}", productSearchParam, r);
        return r;
    }

    /**
     * 根据商品id集合查询商品数据
     *
     * @param productIds
     * @return
     */
    @Cacheable(value = "list.product",key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",productIds);
        List<Product> products = productMapper.selectList(queryWrapper);
        R ok = R.ok("类别信息查询成功", products);
        log.info("ProductServiceImpl.ids, productIds= {}, result:{}",productIds,ok);
        return ok;
    }

    /**
     * 根据商品Id，查询商品ID集合
     *
     * @param productIds
     * @return
     */
    @Override
    public List<Product> cartList(List<Integer> productIds) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",productIds);
        List<Product> products = productMapper.selectList(queryWrapper);
        log.info("ProductServiceImpl.cartList, productIds= {}, result:{}",productIds,products);
        return products;
    }
}
