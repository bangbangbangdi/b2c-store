package com.atguigu.cart.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.cart.config
 * className:      CartConfiguration
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/3 15:47
 * version:    1.0
 */
@Configuration
public class CartConfiguration {

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
