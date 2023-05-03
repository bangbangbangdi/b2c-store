package com.atguigu.doc;

import com.atguigu.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.pojo.doc
 * @className: ProductDoc
 * @author: BangDi
 * @description: 用于存储商品数据的实体类
 * @date: 2023/5/1 11:23
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class ProductDoc extends Product {

    /**
     * 商品名称、商品标题、商品描述的综合值
     */
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(), product.getProductName(), product.getCategoryId(),
                product.getProductTitle(), product.getProductIntro(), product.getProductPicture(),
                product.getProductPrice(), product.getProductSellingPrice(), product.getProductNum(), product.getProductSales());
        this.all = product.getProductName() + product.getProductTitle() + product.getProductIntro();
    }

}