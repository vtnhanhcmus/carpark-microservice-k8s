package com.carpark.carparks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarParksApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarParksApplication.class, args);
	}

}
