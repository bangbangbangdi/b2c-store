package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user") // mybatis-plus相关
public class User implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO) // 主键自增长
    private Integer userId;
    private String userName;
    private String password;
    private String userPhonenumber;

}
