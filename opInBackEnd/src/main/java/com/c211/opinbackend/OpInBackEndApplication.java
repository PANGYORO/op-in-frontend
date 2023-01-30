package com.c211.opinbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// @SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class OpInBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpInBackEndApplication.class, args);
	}

}
