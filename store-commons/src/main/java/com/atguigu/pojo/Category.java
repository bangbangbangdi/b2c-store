package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.pojo
 * @className: Category
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/29 23:07
 * @version: 1.0
 */
@TableName("category")
@Data
public class Category implements Serializable {
    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("category_id")
    private Integer categoryId;
    @JsonProperty("category_name")
    private String categoryName;

}
