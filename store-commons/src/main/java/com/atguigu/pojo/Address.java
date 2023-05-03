package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.pojo
 * @className: Address
 * @author: BangDi
 * @description: 地址实体类
 * @date: 2023/4/29 00:54
 * @version: 1.0
 */
@Data
@TableName("address")
public class Address implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank
    private String linkman;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
    @TableField("user_id")
    private Integer userId;

}
