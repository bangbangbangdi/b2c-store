package com.atguigu.collect;

import com.atguigu.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.collect
 * @className: CollectApplication
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/2 11:14
 * @version: 1.0
 */
@MapperScan(basePackages = "com.atguigu.collect.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class CollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class, args);
    }
}
