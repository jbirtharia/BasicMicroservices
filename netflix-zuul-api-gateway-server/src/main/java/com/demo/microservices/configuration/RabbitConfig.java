package com.demo.microservices.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("rabbitmq.uri")
public class RabbitConfig {

    @Value("{rabbitmq.exchange}")
    private String exchange;

    private String queue;

    private String routingkey;
}
