package com.atguigu.cart;

import com.atguigu.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.cart
 * @className: CartApplication
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/2 20:55
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.cart.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }

}
