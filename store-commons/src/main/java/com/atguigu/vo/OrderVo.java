package com.atguigu.vo;

import com.atguigu.pojo.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.vo
 * className:      OrderVo
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/3 21:05
 * version:    1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderVo extends Order {

    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;

}
