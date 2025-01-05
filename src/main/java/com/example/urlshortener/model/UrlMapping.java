package com.example.urlshortener.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "url_mapping") // This maps the class to a database table named 'url_mapping'.
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment the ID for each new record.
    private Long id;

    @Column(name = "long_url", nullable = false) // original URL provided by the user.
    private String longUrl;

    @Column(name = "short_url", nullable = false, unique = true) // The shortened version of the unique URL.
    private String shortUrl;

    @Column(name = "created_at", nullable = false, updatable = false) // timestamp when the short URL was created.
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "access_count", nullable = false) // Tracks the times short URL has been accessed.
    private int accessCount = 0;

    @Column(name = "expiration_date", nullable = false) // Determines when the URL should expire.
    private LocalDateTime expirationDate;

    // Getter method for the ID.
    public Long getId() {
        return id;
    }

    // Setter method for the ID.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter method for the long URL.
    public String getLongUrl() {
        return longUrl;
    }

    // Setter method for the long URL.
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    // Getter method for the short URL.
    public String getShortUrl() {
        return shortUrl;
    }

    // Setter method for the short URL.
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    // Getter method for the timestamp when the URL was created.
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setter method for the timestamp.
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getter method for the access count for the short URL.
    public int getAccessCount() {
        return accessCount;
    }

    // Setter method for access count.
    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    // Getter method for the expiration date of the short URL.
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    // Setter method for the expiration date.
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
