package com.atguigu.param;

import com.atguigu.pojo.Product;
import lombok.Data;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.param
 * className:      ProductSaveParam
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/5 16:29
 * version:    1.0
 */
@Data
public class ProductSaveParam extends Product {

    /**
     * 保存商品详情的图片地址!图片之间使用 + 拼接
     */
    private String pictures;

}
