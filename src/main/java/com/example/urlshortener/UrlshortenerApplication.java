package com.example.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main entry point for the URL Shortener Spring Boot application.
 * This class initializes and starts the entire application.
 */

@SpringBootApplication
@EnableScheduling
public class UrlshortenerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlshortenerApplication.class, args);
    }
}
