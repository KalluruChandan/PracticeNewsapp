package com.newsapp.userprofile;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "${spring.application.name}",
				description = "This service helps user to register to News Application."
		)
)
public class NewsappUserProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsappUserProfileApplication.class, args);
	}

}
