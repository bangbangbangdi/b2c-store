package com.atguigu.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.carousel
 * @className: CarouselApplicaton
 * @author: BangDi
 * @description: TODO
 * @date: 2023/4/29 15:13
 * @version: 1.0
 */
@MapperScan(basePackages = "com.atguigu.carousel.mapper")
@SpringBootApplication
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }
}
