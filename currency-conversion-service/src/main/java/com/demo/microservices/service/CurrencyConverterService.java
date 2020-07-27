package com.demo.microservices.service;

import com.demo.microservices.beans.CurrencyConversionBean;
import com.demo.microservices.proxy.CurrencyExchangeServiceProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class CurrencyConverterService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @Autowired
    RabbitMQSender sender;

    public CurrencyConversionBean getConvertedCurrency(String from, String to, BigDecimal quantity){

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        CurrencyConversionBean response =
                restTemplate.getForEntity(environment.getProperty("exchange.service.uri"),
                        CurrencyConversionBean.class, uriVariables).getBody();

        log.info("Response from API : \n {}",response);
        return new CurrencyConversionBean(response.getId(),
                from, to, response.getConversionMultiple(), quantity,
                response.getConversionMultiple().multiply(quantity), response.getPort());
    }

    /*API calling using feign.
    * It is used to call other services between application.*/
    public CurrencyConversionBean getConvertedCurrencyFeign(String from, String to, BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

        log.info("Response from API in Currency Converter Service : \n {}",response);
        sender.send(response);
        return new CurrencyConversionBean(response.getId(),
                from, to, response.getConversionMultiple(), quantity,
                response.getConversionMultiple().multiply(quantity), response.getPort());
    }
}
