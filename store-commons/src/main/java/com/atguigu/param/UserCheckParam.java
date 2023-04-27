package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCheckParam {

    @NotBlank
    private String userName; // 注意：参数名称要等于前端传递的JSON key的名称


}
