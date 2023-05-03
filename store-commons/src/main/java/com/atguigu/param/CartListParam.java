package com.atguigu.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * projectName: b2c-store
 * package: com.atguigu.param
 * className: CartListParam
 * author: BangDi
 * description: TODO
 * date: 2023/5/2 22:56
 * version: 1.0
 */
@Data
public class CartListParam {

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

}
