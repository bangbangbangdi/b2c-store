package com.atguigu.product.service.impl;

import com.atguigu.clients.*;
import com.atguigu.param.ProductHotParam;
import com.atguigu.param.ProductIdsParam;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Category;
import com.atguigu.pojo.Picture;
import com.atguigu.pojo.Product;
import com.atguigu.product.mapper.PictureMapper;
import com.atguigu.product.mapper.ProductMapper;
import com.atguigu.product.service.ProductService;
import com.atguigu.to.OrderToProduct;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private SearchClient searchClient;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private CartClient cartClient;
    @Autowired
    private CollectClient collectClient;


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
    @Cacheable(value = "list.product", key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);
        List<Product> products = productMapper.selectList(queryWrapper);
        R ok = R.ok("类别信息查询成功", products);
        log.info("ProductServiceImpl.ids, productIds= {}, result:{}", productIds, ok);
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
        queryWrapper.in("product_id", productIds);
        List<Product> products = productMapper.selectList(queryWrapper);
        log.info("ProductServiceImpl.cartList, productIds= {}, result:{}", productIds, products);
        return products;
    }

    /**
     * 修改库存和增加销售量
     *
     * @param orderToProducts
     */
    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {
        // 将集合转为map productId orderToProduct
        Map<Integer, OrderToProduct> map = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));
        // 获取商品的id集合
        Set<Integer> productIds = map.keySet();
        // 查询集合对应的商品信息
        List<Product> products = productMapper.selectBatchIds(productIds);
        // 修改商品信息
        for (Product product : products) {
            Integer num = map.get(product.getProductId()).getNum();
            product.setProductNum(product.getProductNum() - num); // 减库存
            product.setProductSales(product.getProductSales() + num); // 添加销售量
        }
        // 批量更新
        updateBatchById(products);
        log.info("ProductServiceImpl.subNumber, orderToProducts= {}, result:{}", orderToProducts, "库存和销售量修改完毕");
    }

    /**
     * 类别对应的商品数量查询
     *
     * @param categoryId
     * @return
     */
    @Override
    public Long adminCount(Integer categoryId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        Long cnt = productMapper.selectCount(queryWrapper);
        log.info("ProductServiceImpl.adminCount, categoryId= {}, result:{}", categoryId, cnt);
        return cnt;
    }

    /**
     * 商品保存业务
     *
     * @param productSaveParam
     * @return
     */
    @CacheEvict(value = "list.product", allEntries = true)
    @Override
    public R adminSave(ProductSaveParam productSaveParam) {
        // 1.商品数据保存
        Product product = new Product();
        BeanUtils.copyProperties(productSaveParam, product);
        int rows = productMapper.insert(product);
        log.info("ProductServiceImpl.adminSave, productSaveParam= {}, result:{}", productSaveParam, rows);

        // 2.商品图片数据保存
        String pictures = productSaveParam.getPictures();
        if (!StringUtils.isEmpty(pictures)) {
            String[] urls = pictures.split("\\+");
            for (String url : urls) {
                Picture picture = new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(url);
                pictureMapper.insert(picture); // 插入商品的图片
            }
        }
        // 3.搜索数据库的数据添加
        searchClient.saveOrUpdate(product);

        return R.ok("商品数据添加成功");
    }

    /**
     * 商品数据更新
     * 1.更新商品数据
     * 2.同步搜索服务
     *
     * @param product
     * @return
     */
    @Override
    public R adminUpdate(Product product) {
        productMapper.updateById(product);
        searchClient.saveOrUpdate(product);
        return R.ok("商品数据更新成功");
    }

    /**
     * 商品删除业务
     *
     * @param productId
     * @return
     */

    @Caching(
            evict = {
                    @CacheEvict(value = "product.list", allEntries = true),
                    @CacheEvict(value = "product", key = "#productId")
            }
    )
    @Override
    public R adminRemove(Integer productId) {
        //1.检查购物车
        R r = cartClient.check(productId);
        if ("004".equals(r.getCode())) {
            log.info("ProductServiceImpl.adminRemove, productId= {}, result:{}", productId, r);
            return r;
        }
        //2.检查订单
        r = orderClient.check(productId);
        if ("004".equals(r.getCode())) {
            log.info("ProductServiceImpl.adminRemove, productId= {}, result:{}", productId, r);
            return r;
        }
        //3.删除商品
        productMapper.deleteById(productId);
        //4.删除商品相关的图片
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        int rows = pictureMapper.delete(queryWrapper);
        //5.删除收藏
        collectClient.remove(productId);
        //6.进行es数据同步
        searchClient.remove(productId);
        return R.ok("商品删除成功");
    }
}