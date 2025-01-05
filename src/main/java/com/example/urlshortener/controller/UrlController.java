package com.example.urlshortener.controller;

import com.example.urlshortener.DTO.UrlRequest;
import com.example.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controller for handling URL shortening and redirection requests.
 */
@RestController
@RequestMapping("/shorty")
public class UrlController {

    // Injecting the service layer for business logic handling
    @Autowired
    private UrlService service;

    /**
     * API endpoint for shortening a URL.
     * Accepts a URL and optional expiration date, generates a shortened URL, and returns it to the client.
     */
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest request) {
        try {
            LocalDateTime expirationDate = null;
            if (request.getExpirationDate() != null) {
                expirationDate = LocalDateTime.parse(request.getExpirationDate());
            }

            String shortUrl = service.createShortUrl(request.getLongUrl(), expirationDate);
            return ResponseEntity.ok("shorty/" + shortUrl);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid URL: " + request.getLongUrl());
        }
    }

    /**
     * API endpoint for redirecting to the original URL using the shortened URL.
     * If the short URL is valid and not expired, the user will be redirected.
     */
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortUrl) {
        return service.getOriginalUrl(shortUrl)
                .map(url -> ResponseEntity.status(302).header("Location", url).<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }


}
