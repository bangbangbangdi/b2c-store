package com.atguigu.product.listener;

import com.atguigu.product.service.ProductService;
import com.atguigu.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * projectName:    b2c-store
 * package:        com.atguigu.product.listener
 * className:      ProductRabbitMqListener
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/3 16:03
 * version:    1.0
 */

@Component
public class ProductRabbitMqListener {

    @Autowired
    private ProductService productService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sub.queue"),
                    exchange = @Exchange("topic.ex"),
                    key = "sub.number"
            )
    )
    public void subNumber(List<OrderToProduct> orderToProducts){
        productService.subNumber(orderToProducts);
    }

}
