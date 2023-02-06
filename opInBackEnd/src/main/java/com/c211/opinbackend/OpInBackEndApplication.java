package com.c211.opinbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@PropertySource("classpath:properties/env.properties")
public class OpInBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpInBackEndApplication.class, args);
	}

}
