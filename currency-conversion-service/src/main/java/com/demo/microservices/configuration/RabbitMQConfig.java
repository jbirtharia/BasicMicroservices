package com.demo.microservices.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    RabbitConfig config;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Bean
    Queue queue() {
        return new Queue(config.getQueue(), false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(config.getExchange());
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(config.getRoutingkey());
    }
}