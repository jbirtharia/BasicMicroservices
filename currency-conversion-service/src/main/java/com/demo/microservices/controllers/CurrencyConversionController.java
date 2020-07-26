package com.demo.microservices.controllers;

import com.demo.microservices.beans.CurrencyConversionBean;
import com.demo.microservices.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyConverterService service;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        return service.getConvertedCurrency(from, to, quantity);
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        return service.getConvertedCurrencyFeign(from, to, quantity);
    }
}
