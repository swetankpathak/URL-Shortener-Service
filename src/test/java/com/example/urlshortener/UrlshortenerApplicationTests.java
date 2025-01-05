package com.example.urlshortener;

import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UrlshortenerApplicationTests {

	@Mock
	private UrlRepository urlRepository;

	@InjectMocks
	private UrlService urlService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testShortenUrl_Success() {
		String longUrl = "https://start.spring.io/";
		LocalDateTime expirationDate = LocalDateTime.now().plusHours(24);

		when(urlRepository.findByLongUrl(longUrl)).thenReturn(Optional.empty());

		String shortUrl = urlService.createShortUrl(longUrl, expirationDate);

		assertNotNull(shortUrl);
		verify(urlRepository, times(1)).save(any(UrlMapping.class));
	}

	@Test
	void testShortenUrl_InvalidUrl() {
		String invalidUrl = "https//start.spring.io/";

		Exception exception = assertThrows(IllegalArgumentException.class, () ->
				urlService.createShortUrl(invalidUrl, LocalDateTime.now().plusMinutes(10))
		);

		assertEquals("Invalid URL: " + invalidUrl, exception.getMessage());
	}

	@Test
	void testGetOriginalUrl_Success() {
		String shortUrl = "12345678";
		UrlMapping mapping = new UrlMapping();
		mapping.setLongUrl("https://start.spring.io/");
		mapping.setShortUrl(shortUrl);
		mapping.setExpirationDate(LocalDateTime.now().plusMinutes(10));

		when(urlRepository.findByShortUrl(shortUrl)).thenReturn(Optional.of(mapping));

		Optional<String> originalUrl = urlService.getOriginalUrl(shortUrl);

		assertTrue(originalUrl.isPresent());
		assertEquals("https://start.spring.io/", originalUrl.get());
	}

	@Test
	void testGetOriginalUrl_Expired() {
		String shortUrl = "12345678";
		UrlMapping mapping = new UrlMapping();
		mapping.setLongUrl("https://start.spring.io/");
		mapping.setShortUrl(shortUrl);
		mapping.setExpirationDate(LocalDateTime.now().minusMinutes(10));

		when(urlRepository.findByShortUrl(shortUrl)).thenReturn(Optional.of(mapping));

		Optional<String> originalUrl = urlService.getOriginalUrl(shortUrl);

		assertFalse(originalUrl.isPresent());
		verify(urlRepository, times(1)).delete(mapping);
	}


	@Test
	void testContextLoads() {
		assertTrue(true);  // Simple test to ensure the context loads without issues
	}
}
