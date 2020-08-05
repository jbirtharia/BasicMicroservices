package com.demo.microservices;

import brave.sampler.Sampler;
import com.demo.microservices.beans.ExchangeValue;
import com.demo.microservices.repository.ExchangeValueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}


	/*For Spring Cloud Sleuth
	 * It will maintain unique Id of each request which
	 * will help in to trace the request between different services and
	 * maintain its log.*/
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public CommandLineRunner demo(ExchangeValueRepository repository) {
		return args -> {
			repository.save(new ExchangeValue(1L,"USD","INR",BigDecimal.valueOf(65.00),0));
			repository.save(new ExchangeValue(2L,"AUD","INR",BigDecimal.valueOf(55.00),0));
			repository.save(new ExchangeValue(3L,"EUR","INR",BigDecimal.valueOf(75.00),0));
			repository.save(new ExchangeValue(4L,"SGD","INR",BigDecimal.valueOf(52.00),0));
		};
	}
}
