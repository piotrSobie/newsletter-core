package com.example.newslettercore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NewsletterCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsletterCoreApplication.class, args);
	}

}
