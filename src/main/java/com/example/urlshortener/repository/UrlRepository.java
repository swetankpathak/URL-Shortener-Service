package com.example.urlshortener.repository;

import com.example.urlshortener.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for performing database operations related to URL mappings.
 */
@Repository
public interface UrlRepository extends JpaRepository<UrlMapping, Long> {

    /**
     * Finds a URL mapping by its shortened URL.
     * This helps retrieve the original URL when a user tries to access the shortened URL.
     */
    Optional<UrlMapping> findByShortUrl(String shortUrl);

    /**
     * Finds a URL mapping by its long/original URL.
     * This is useful for checking if a URL has already been shortened.
     */
    Optional<UrlMapping> findByLongUrl(String longUrl);
}
