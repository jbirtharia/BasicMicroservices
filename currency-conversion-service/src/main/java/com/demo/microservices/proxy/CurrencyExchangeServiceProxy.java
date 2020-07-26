package com.demo.microservices.proxy;

import com.demo.microservices.beans.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/*By Ribbon Client we don't have to provide complete url.
* Ribbon client will automatically distribute load among different instance of application*/
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")

/*Now currency exchange service has configured with naming server (i.e. Eureka Server)
* So No need to provide url of calling service. Naming server will take care of it.*/
//@FeignClient(name = "currency-exchange-service")

/*This will make call through Zuul API Gateway server.
* Now each call will go through api gateway.*/
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    //@GetMapping("/currency-exchange/from/{from}/to/{to}")
    /*Zuul API gateway uses url by appending service name, hence we are
    * appending service name to url, so this call can happen through api gateway*/
    @GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
