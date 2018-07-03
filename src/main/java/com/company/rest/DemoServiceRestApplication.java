package com.company.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages="com.company.rest")
@EnableJpaRepositories(basePackages="com.company.rest.repository")
@EntityScan(basePackages="com.company.rest.entity")

public class DemoServiceRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceRestApplication.class, args);
	}
}
