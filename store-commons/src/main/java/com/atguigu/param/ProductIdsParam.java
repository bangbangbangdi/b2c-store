package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: ProductIdsParam
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/30 23:05
 * @version: 1.0
 */
@Data
public class ProductIdsParam extends PageParam{

    @NotNull
    private List<Integer> categoryID;

}
