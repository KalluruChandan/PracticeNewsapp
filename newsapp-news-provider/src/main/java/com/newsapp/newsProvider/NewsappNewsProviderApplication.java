package com.newsapp.newsProvider;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "${spring.application.name}",
				description = "This service helps to fetch news article based on the \"topic\" user requested."
		)
)
public class NewsappNewsProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsappNewsProviderApplication.class, args);
	}

}
