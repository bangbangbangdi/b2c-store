package com.atguigu.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.admin
 * className:      AdminApplication
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/4 00:31
 * version:    1.0
 */
@MapperScan(basePackages = "com.atguigu.admin.mapper")
@SpringBootApplication
@EnableCaching
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
