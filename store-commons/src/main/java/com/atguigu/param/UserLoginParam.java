package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: UserLoginParam
 * @author: BangDi
 * @description:用户登录参数实体
 * @date: 2023/4/28 13:35
 * @version: 1.0
 */
@Data
public class UserLoginParam {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;

}
