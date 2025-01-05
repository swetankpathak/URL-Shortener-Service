package com.example.urlshortener.tasks;

import com.example.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UrlCleanupTask {

    @Autowired
    private UrlService urlService;

//    @Scheduled(fixedRate = 60000)
//    public void cleanupExpiredUrls() {
//        urlService.cleanUpExpiredUrls();
//    }
}
