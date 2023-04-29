package com.atguigu.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: AddressListParam
 * @author: BangDi
 * @description: 地址集合参数接受
 * @date: 2023/4/29 09:25
 * @version: 1.0
 */

@Data
public class AddressListParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

}
