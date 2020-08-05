package com.demo.microservices.service;

import com.demo.microservices.configuration.RabbitConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQSender {

    @Autowired
    @Qualifier("amqTemplate")
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitConfig config;

    public void send(String uri) {
        log.info("Object Going For Routing : "+uri);
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), uri);

    }
}
