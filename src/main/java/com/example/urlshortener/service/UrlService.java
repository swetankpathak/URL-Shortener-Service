package com.example.urlshortener.service;

import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service responsible for handling URL shortening, access tracking, and expiration management.
 */
@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    /**
     * Creates a short URL for the given long URL.
     * If the URL already exists and is still valid, the same short URL will be reused.
     * If the URL is expired, a new short URL will be generated.
     */
    public String createShortUrl(String longUrl, LocalDateTime expirationDate) {
        if (!isValidUrl(longUrl)) {
            throw new IllegalArgumentException("Invalid URL: " + longUrl);
        }

        // Check if the URL already exists in the database
        Optional<UrlMapping> existingMapping = repository.findByLongUrl(longUrl);
        if (existingMapping.isPresent()) {
            UrlMapping mapping = existingMapping.get();
            // If the existing URL has expired, generate a new short URL
            if (LocalDateTime.now().isAfter(mapping.getExpirationDate())) {
                String newShortUrl = UUID.randomUUID().toString().substring(0, 8);
                mapping.setShortUrl(newShortUrl);
                mapping.setExpirationDate(LocalDateTime.now().plusHours(24));  // Set expiration for 24 hours
                repository.save(mapping);
                return newShortUrl;
            }
            // If not expired, reuse the existing short URL
            return mapping.getShortUrl();
        }

        // Create a new short URL since the long URL does not exist
        String shortUrl = UUID.randomUUID().toString().substring(0, 8);
        UrlMapping newMapping = new UrlMapping();
        newMapping.setLongUrl(longUrl);
        newMapping.setShortUrl(shortUrl);
        newMapping.setExpirationDate(LocalDateTime.now().plusHours(24));  // Set expiration for 24 hours
        repository.save(newMapping);
        return shortUrl;
    }


    /**
     * Retrieves the original URL based on the provided short URL.
     * If the URL is expired, it deletes the mapping and returns empty.
     */
    public Optional<String> getOriginalUrl(String shortUrl) {
        Optional<UrlMapping> mappingOpt = repository.findByShortUrl(shortUrl);

        // If mapping exists, check for expiration and access count increment
        if (mappingOpt.isPresent()) {
            UrlMapping mapping = mappingOpt.get();

            // Delete the URL if it has expired
            if (mapping.getExpirationDate() != null && mapping.getExpirationDate().isBefore(LocalDateTime.now())) {
                repository.delete(mapping);
                return Optional.empty();
            }

            // Increment the access count for analytics
            mapping.setAccessCount(mapping.getAccessCount() + 1);
            repository.save(mapping);

            return Optional.of(mapping.getLongUrl());
        }

        // If the short URL doesn't exist
        return Optional.empty();
    }

    /**
     * Validates the format of the URL to ensure it is properly structured.
     */
    private boolean isValidUrl(String url) {
        try {
            new java.net.URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}


