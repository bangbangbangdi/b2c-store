package com.atguigu.admin.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin.param
 * className:      AdminUserParam
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 10:06
 * version:    1.0
 */
@Data
public class AdminUserParam {

    @Length(min = 6)
    private String userAccount; //账号
    @Length(min = 6)
    private String userPassword; //密码
    @NotBlank
    private String verCode;  //验证码

}
