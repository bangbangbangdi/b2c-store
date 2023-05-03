package com.atguigu.param;

import com.atguigu.vo.CartVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.param
 * className:      OrderParam
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/3 14:19
 * version:    1.0
 */
@Data
public class OrderParam implements Serializable {
    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;

}
