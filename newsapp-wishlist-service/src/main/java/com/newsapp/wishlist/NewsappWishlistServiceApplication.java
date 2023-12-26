package com.newsapp.wishlist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "${spring.application.name}",
				description = "This service helps user to wishlist the news articles that user liked or wanted to visit them again."
		)
)
public class NewsappWishlistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsappWishlistServiceApplication.class, args);
	}

}
