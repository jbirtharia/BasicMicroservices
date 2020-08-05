package com.demo.microservices.service;

import com.demo.microservices.beans.CurrencyConversionBean;
import com.demo.microservices.configuration.RabbitConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQSender {

    @Autowired
    @Qualifier("amqTemplate")
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitConfig config;

    @SneakyThrows
    public void send(CurrencyConversionBean obj) {
        log.info("Object Going For Routing : "+obj);
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), obj);

    }
}
