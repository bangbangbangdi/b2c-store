package com.atguigu.search;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.atguigu.clients.ProductClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.search
 * @className: SearchApplication
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/1 10:38
 * @version: 1.0
 */
//排除自动导入数据库配置,否者出现为配置连接池信息异常
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class ,
        HibernateJpaAutoConfiguration.class})
@EnableFeignClients(clients = {ProductClient.class})
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }

}
