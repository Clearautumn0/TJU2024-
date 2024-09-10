package com.neusoft.elmboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElmbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElmbootApplication.class, args);
	}

}
