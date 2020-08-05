package com.demo.microservices.service;

import com.demo.microservices.beans.ExchangeValue;
import com.demo.microservices.repository.ExchangeValueRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ExchangeValueService {

    @Autowired
    private ExchangeValueRepository repository;

    public ExchangeValue findCurrencyFromAndTo(String from,String to){
        ExchangeValue exchangeValue = repository.findByFromAndToIgnoreCase(from,to);
        log.info("Exchange Value in Exchange Value Service : \n {}",exchangeValue);
        return exchangeValue;
    }
}
