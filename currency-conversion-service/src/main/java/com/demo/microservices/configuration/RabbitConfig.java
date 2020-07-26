package com.demo.microservices.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("rabbitmq")
public class RabbitConfig {

    private String exchange;

    private String queue;

    private String routingkey;
}
