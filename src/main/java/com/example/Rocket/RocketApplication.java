package com.example.Rocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketApplication {

	private static final Logger log = LoggerFactory.getLogger(RocketApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RocketApplication.class, args);

		log.info("Spring Boot Application Started");
	}

}
