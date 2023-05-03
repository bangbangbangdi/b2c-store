package com.atguigu.order;

import com.atguigu.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * projectName: b2c-store
 * package: com.atguigu.order
 * className: OrderApplication
 * author: BangDi
 * description: TODO
 * date: 2023/5/3 10:17
 * version: 1.0
 */
@MapperScan(basePackages = "com.atguigu.order.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
