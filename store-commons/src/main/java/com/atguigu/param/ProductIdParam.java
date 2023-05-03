package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: ProductIdParam
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/30 23:49
 * @version: 1.0
 */
@Data
public class ProductIdParam {

    @NotNull
    private Integer productID;

}
