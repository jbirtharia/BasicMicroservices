package com.demo.microservices.service;

import com.demo.microservices.configuration.RabbitConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitConfig config;

    public void send(Object obj) {
        log.info("Object Going For Routing : "+obj);
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), obj);

    }
}