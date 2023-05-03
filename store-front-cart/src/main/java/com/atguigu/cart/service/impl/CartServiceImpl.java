package com.atguigu.cart.service.impl;

import com.atguigu.cart.mapper.CartMapper;
import com.atguigu.cart.service.CartService;
import com.atguigu.clients.ProductClient;
import com.atguigu.param.CartSaveParam;
import com.atguigu.param.ProductCollectParam;
import com.atguigu.param.ProductIdParam;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import com.atguigu.vo.CartVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.cart.service.impl
 * @className: CartServiceImpl
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/2 21:57
 * @version: 1.0
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private CartMapper cartMapper;

    /**
     * 购物车数据添加方法
     *
     * @param cartSaveParam
     * @return 001 成功,002 已经存在num+1,003库存为0无法添加
     */
    @Override
    public R save(CartSaveParam cartSaveParam) {
        //1.查询商品数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartSaveParam.getProductId());

        Product product = productClient.productDetail(productIdParam);
        if (product == null){
            return R.fail("商品已经被删除，无法添加到购物车");
        }
        //2.检查库存
        if (product.getProductNum() == 0){
            R ok = R.ok("没有库存数据，无法购买");
            ok.setCode("003");
            log.info("CartServiceImpl.save, cartSaveParam= {}, result:{}",cartSaveParam,ok);
            return ok;
        }

        //3.检查是否添加过
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cartSaveParam.getUserId());
        queryWrapper.eq("product_id",cartSaveParam.getProductId());
        Cart cart = cartMapper.selectOne(queryWrapper);
        if (cart != null) {
            // 证明该商品添加过购物车
            // 将原有到数量+1,返回002提示即可
            cart.setNum(cart.getNum() + 1);
            cartMapper.updateById(cart);
            R ok = R.ok("购物车存在该商品，数量+1");
            ok.setCode("002");
            log.info("CartServiceImpl.save, cartSaveParam= {}, result:{}",cartSaveParam,ok);
            return ok;
        }

        //4.添加购物车
        cart = new Cart();
        cart.setNum(1);
        cart.setUserId(cartSaveParam.getUserId());
        cart.setProductId(cartSaveParam.getProductId());
        int rows = cartMapper.insert(cart);
        log.info("CartServiceImpl.save, cartSaveParam= {}, result:{}",cartSaveParam,rows);
        CartVo cartVo = new CartVo(product, cart);

        //5.结果封装和返回
        return R.ok("购物车数据添加成功",cartVo);
    }

    /**
     * 返回购物车数据
     *
     * @param userId
     * @return 确保要返回一个数组或者集合
     */
    @Override
    public R list(Integer userId) {
        // 1.用户id查询，购物车数据
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Cart> carts = cartMapper.selectList(queryWrapper);
        // 2.判断是否存在,不存在,返回一个空集合
        if (carts == null || carts.size() ==0){
            carts = new ArrayList<>();
            return R.ok("购物车为空",carts);
        }
        // 3.存在则获取商品的id集合，并且调用商品服务查询
        ArrayList<Integer> productIds = new ArrayList<>();
        for (Cart cart : carts) {
            productIds.add(cart.getProductId());
        }
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> products = productClient.cartList(productCollectParam);
        // 商品集合 - 商品map 商品id = key 商品 = value
        Map<Integer, Product> productMap = products.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        // 4.进行vo的封装
        ArrayList<CartVo> cartVos = new ArrayList<>();
        for (Cart cart : carts) {
            cartVos.add(new CartVo(productMap.get(cart.getProductId()),cart));
        }
        R ok = R.ok("数据库数据查询成功", cartVos);
        log.info("CartServiceImpl.list, userId= {}, result:{}",userId,ok);
        return ok;
    }

    /**
     * 更新购物车业务
     *
     * @param cart
     * @return
     */
    @Override
    public R update(Cart cart) {
        // 查询商品数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cart.getProductId());
        Product product = productClient.productDetail(productIdParam);

        // 判断库存是否可用
        if (product == null || cart.getNum() > product.getProductNum()){
            log.info("CartServiceImpl.update, cart= {}, result:{}",cart,"修改失败,库存不足");
            return R.fail("修改失败,库存不足");
        }

        // 根据判断修改购物车表,能够调用该接口说明购物车已经存在,因此不需要做null判断(应该)
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());
        Cart newCart = cartMapper.selectOne(queryWrapper);
        newCart.setNum(cart.getNum());
        int rows = cartMapper.updateById(newCart);
        log.info("CartServiceImpl.update, cart= {}, result:{}",cart,rows);
        return R.ok("修改购物车数量成功");
    }

    /**
     * 删除购物车数据
     *
     * @param cart
     * @return
     */
    @Override
    public R remove(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());

        int rows = cartMapper.delete(queryWrapper);
        log.info("CartServiceImpl.remove, cart= {}, result:{}",cart,rows);
        return R.ok("购物车删除成功");
    }

    /**
     * 清空对应id的购物车项
     *
     * @param cartIds
     */
    @Override
    public void clearIds(List<Integer> cartIds) {
        cartMapper.deleteBatchIds(cartIds);
        log.info("CartServiceImpl.clearIds, cartIds= {}, result:{}",cartIds,cartIds);
    }
}
