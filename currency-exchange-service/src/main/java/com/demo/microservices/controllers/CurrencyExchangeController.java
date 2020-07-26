package com.demo.microservices.controllers;

import com.demo.microservices.beans.ExchangeValue;
import com.demo.microservices.service.ExchangeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueService service;


    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue value = service.findCurrencyFromAndTo(from,to);
        value.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return value;
    }
}
