package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: ProductPromoParam
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/29 22:56
 * @version: 1.0
 */
@Data
public class ProductPromoParam {

    @NotBlank
    private String categoryName;

}
