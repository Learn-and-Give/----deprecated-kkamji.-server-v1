package com.kkamjidot.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.validation.Valid;

@SpringBootApplication
public class ApiServerV1Application {
	public static void main(String[] args) {
		SpringApplication.run(ApiServerV1Application.class, args);
	}
}
