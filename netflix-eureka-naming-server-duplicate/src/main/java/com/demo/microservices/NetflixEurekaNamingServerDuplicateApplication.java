package com.demo.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaNamingServerDuplicateApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaNamingServerDuplicateApplication.class, args);
	}

}
