package com.newsapp.userprofile;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "${spring.application.name}",
				description = "This service helps user to register to News Application."
		)
)
@Slf4j
public class NewsappUserProfileApplication {
	public static void main(String[] args) {
		SpringApplication.run(NewsappUserProfileApplication.class, args);
		log.info("Application started");
	}
}
