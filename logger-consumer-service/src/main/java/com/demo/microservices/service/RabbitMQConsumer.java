package com.demo.microservices.service;

import com.demo.microservices.beans.CurrencyConversionBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMQConsumer {

    @RabbitListener(queues = "${rabbitmq.bean-queue}")
    public void recievedMessage(CurrencyConversionBean obj, Message message) {
        log.info("Routing Key : "+message.getMessageProperties().getReceivedRoutingKey());
        log.info("Recieved Message From RabbitMQ : {}",obj);
    }

    @RabbitListener(queues = "${rabbitmq.uri-queue}")
    public void recievedURIMessage(String uri, Message message) {
        log.info("Routing Key : "+message.getMessageProperties().getReceivedRoutingKey());
        log.info("Recieved Message From RabbitMQ : {}",uri);
    }

   /* @RabbitListener(queues = "${rabbitmq.queue}")
    public void recievedMessage(Object obj) {
        log.info("Recieved Message From RabbitMQ : {}",obj);
    }*/
}
