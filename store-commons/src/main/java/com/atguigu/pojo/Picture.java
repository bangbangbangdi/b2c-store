package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.pojo
 * @className: Pictrue
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/1 00:45
 * @version: 1.0
 */
@Data
@TableName("product_picture")
public class Picture implements Serializable {
    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @JsonProperty("product_id")
    @TableField("product_id")
    private Integer productId;

    @JsonProperty("product_picture")
    @TableField("product_picture")
    private String productPicture;

    private String intro;

}
