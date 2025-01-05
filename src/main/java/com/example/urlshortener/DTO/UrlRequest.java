package com.example.urlshortener.DTO;

/**
 * This DTO file is for handling incoming URL shortening requests.
 * This class is used to capture data from the frontend (like the URL and expiration date)
 * and transfer it to the service layer for processing.
 */
public class UrlRequest {

    // The original long URL provided by the user for shortening.
    private String longUrl;

    // Expiration date for the shortened URL.
    private String expirationDate;

    // Getter for the long URL
    public String getLongUrl() {
        return longUrl;
    }

    // Setter for the long URL
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    // Getter for the expiration date
    public String getExpirationDate() {
        return expirationDate;
    }

    // Setter for the expiration date
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
