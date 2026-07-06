package com.example.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UrlRequest;
import com.example.demo.dto.UrlResponse;
import com.example.demo.entity.ShortURLMapping;
import com.example.demo.service.UrlService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UrlController {
	
	private UrlService urlservice;
	
	public UrlController(UrlService urlservice) {
		super();
		this.urlservice = urlservice;
	}
	
	// Get all URLs API working fine
	@GetMapping("/geturls")
	public Page<ShortURLMapping> getAllURLs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	    return urlservice.getAllURLs(page, size);
	}
	
	@GetMapping("/{shortUrl}")
	public void redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
		String originalUrl = urlservice.getOriginalUrl(shortUrl);
		response.sendRedirect(originalUrl);
	}
	
	// Working fine
	@PostMapping("/shorten")
	 public UrlResponse shorten(@RequestBody UrlRequest request) {
	        return urlservice.shortenUrl(request);
	}
	
	// Delete API working fine
	@DeleteMapping("/geturls/{urlId}")
	public ResponseEntity<Void> deleteUrl(@PathVariable Long urlId) {
		urlservice.deleteUrl(urlId);
	    return ResponseEntity.noContent().build();
	}
}
