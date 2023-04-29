package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: AddressRemoveParam
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/29 10:29
 * @version: 1.0
 */
@Data
public class AddressRemoveParam {

    @NotNull
    private Integer id;

}
