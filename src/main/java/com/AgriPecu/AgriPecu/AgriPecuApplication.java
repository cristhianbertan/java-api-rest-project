package com.AgriPecu.AgriPecu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AgriPecuApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgriPecuApplication.class, args);
	}

}