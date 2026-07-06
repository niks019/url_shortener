package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UrlRequest;
import com.example.demo.dto.UrlResponse;
import com.example.demo.entity.ShortURLMapping;
import com.example.demo.repository.RedisRepository;
import com.example.demo.repository.UrlRepository;
import com.example.demo.util.ShortenUtility;

@Service
public class UrlService {

	@Autowired
	private UrlRepository repository;
	
	@Autowired
    private StringRedisTemplate redisTemplate;
	
	private static final String BASE_URL = "http://localhost:8080/";

	public UrlService(UrlRepository repository, StringRedisTemplate redisTemplate) {
		super();
		this.repository = repository;
		this.redisTemplate = redisTemplate;
	}	
	
	// working fine
	public UrlResponse shortenUrl(UrlRequest urlRequest) {
		
		String originalUrl = urlRequest.longUrl();
		System.out.println(originalUrl);
		
		Optional<ShortURLMapping> existingUrl = repository.findByLongUrl(originalUrl);
		
		if(existingUrl.isPresent()) {
			ShortURLMapping urlMapping1 = existingUrl.get();
			urlMapping1.setAccessStats(urlMapping1.getAccessStats()+1);
			repository.save(urlMapping1);
			return new UrlResponse(originalUrl, BASE_URL+existingUrl.get().getShortenedUrl());
		}
		
		ShortURLMapping urlMapping = new ShortURLMapping();
		urlMapping.setLongUrl(originalUrl);
		urlMapping.setAccessStats(1);
		urlMapping.setCreatedAt(LocalDateTime.now());
		
		ShortURLMapping savedData = repository.save(urlMapping);
		
		String shortUrl = ShortenUtility.encodeString(urlMapping.getUrlId());
		System.out.println(shortUrl);
		savedData.setShortenedUrl(shortUrl);
		repository.save(savedData);
		
		// setting in redis
		redisTemplate.opsForValue().set(shortUrl, originalUrl, 24, TimeUnit.HOURS);
		return new UrlResponse(originalUrl, BASE_URL+shortUrl);
				
	}
	
	public String getOriginalUrl(String shortUrl) {
		String cached = redisTemplate.opsForValue().get(shortUrl);
		if(cached != null) {
			return cached;
		}
		
		ShortURLMapping urlMapping = repository.findByShortenedUrl(shortUrl)
				.orElseThrow(() -> new RuntimeException("URL not found"));
		
		redisTemplate.opsForValue().set(shortUrl, urlMapping.getLongUrl(), 24, TimeUnit.HOURS);
		return urlMapping.getLongUrl();
	}
	
	// Working fine
	public void deleteUrl(long urlId) {
		repository.deleteById(urlId);
	}
	
	// working fine but commented to implement pagination
//	public List<ShortURLMapping> getAllURLs() {
//        return repository.findAll();
//    }
	
	public Page<ShortURLMapping> getAllURLs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("urlId").ascending());
        return repository.findAll(pageable);
    }
	
}
