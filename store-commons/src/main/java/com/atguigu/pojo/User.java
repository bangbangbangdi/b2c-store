package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("user") // mybatis-plus相关
public class User implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    @TableId(type = IdType.AUTO) // 主键自增长
    private Integer userId;
    @Length(min = 6)
    private String userName;

    @JsonInclude(JsonInclude.Include.NON_NULL) // 当这个值不为空的时候生成json,为null不生成
    @NotBlank
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank
    private String userPhonenumber;

}
