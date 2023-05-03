package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: ProductCollectParam
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/2 16:19
 * @version: 1.0
 */
@Data
public class ProductCollectParam {

    @NotEmpty
    private List<Integer> productIds;

}
