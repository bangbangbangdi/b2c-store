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
 * @className: Carousel
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/29 15:20
 * @version: 1.0
 */
@TableName("carousel")
@Data
public class Carousel implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("carousel_id")
    @TableId(value = "carousel_id",type = IdType.AUTO)
    private Integer carouselId;
    @TableField("img_path")
    private String imgPath;
    private String describes;
    @JsonProperty("product_id")
    @TableField("product_id")
    private Integer productId;
    private Integer priority;

}
