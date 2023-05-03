package com.atguigu.param;

import lombok.Data;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: ProductSearchParam
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/1 15:08
 * @version: 1.0
 */
@Data
public class ProductSearchParam extends PageParam {

    private String search;

}
