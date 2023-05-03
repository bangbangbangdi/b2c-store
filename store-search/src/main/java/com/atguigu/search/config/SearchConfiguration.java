package com.atguigu.search.config;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.search.config
 * @className: SearchConfiguration
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/1 11:52
 * @version: 1.0
 */

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * projectName: b2c-cloud-store
 *
 * @author: 赵伟风
 * description: 消息队列配置
 */
@Configuration
public class SearchConfiguration {

    /**
     * mq序列化方式，选择json！
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * es客户端添加到ioc容器
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client =
                new RestHighLevelClient(
                        RestClient.builder(HttpHost.create("http://49.235.119.27:9200")));
        return client;
    }
}
