package com.demo.microservices.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMQConsumer {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void recievedMessage(Object obj) {
        log.info("Recieved Message From RabbitMQ : {}",obj);
    }

}
