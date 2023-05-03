package com.atguigu.param;

import com.atguigu.pojo.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.param
 * className:      AddressParam
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/3 21:59
 * version:    1.0
 */
@Data
public class AddressParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
    private Address add;

}
