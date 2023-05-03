package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: ProductHotParam
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/30 19:46
 * @version: 1.0
 */
@Data
public class ProductHotParam {

    @NotEmpty
    private List<String> categoryName;

}
