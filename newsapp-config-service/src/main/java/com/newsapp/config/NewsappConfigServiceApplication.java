package com.newsapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableConfigServer
@RefreshScope
public class NewsappConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsappConfigServiceApplication.class, args);
	}

}
