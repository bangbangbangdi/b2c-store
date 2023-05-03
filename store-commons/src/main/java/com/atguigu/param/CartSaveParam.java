package com.atguigu.param;

import com.atguigu.utils.R;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: CartSaveParam
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/2 21:41
 * @version: 1.0
 */
@Data
public class CartSaveParam {

    @JsonProperty("product_id")
    @NotNull
    private Integer productId;
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

}
