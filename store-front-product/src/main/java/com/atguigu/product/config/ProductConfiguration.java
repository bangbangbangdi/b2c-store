package com.atguigu.product.config;

import com.atguigu.config.CacheConfiguration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.product.config
 * @className: ProductConfiguration
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/1 21:43
 * @version: 1.0
 */
@Configuration
public class ProductConfiguration extends CacheConfiguration {

    /**
     * mq序列化方式，选择json！
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
