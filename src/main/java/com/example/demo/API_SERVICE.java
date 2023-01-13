package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.demo"})
@EntityScan({"com.example.demo"})
@EnableJpaRepositories({"com.example.demo"})
public class API_SERVICE {

	public static void main(String[] args) {
		SpringApplication.run(API_SERVICE.class, args);
	}


}
