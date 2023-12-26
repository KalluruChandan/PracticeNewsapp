package com.newsapp.auth;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "${spring.application.name}",
				description = "This service helps user to login and also helps to check the user with given credentials are valid or not."
		)
)
public class NewsappAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsappAuthServiceApplication.class, args);
	}

}
