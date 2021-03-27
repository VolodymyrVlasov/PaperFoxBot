package com.ua.paperfox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class PaperfoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaperfoxApplication.class, args);
	}
}
