package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="short_urls")
public class ShortURLMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long urlId;
	
	@Column
	private String longUrl;
	
	@Column(unique = true)
	private String shortenedUrl;
	
	@Column
	private long accessStats;

	@Column
	private LocalDateTime createdAt;
	
	// Empty Constructor
	public ShortURLMapping() {}

	// Parameterized Constructor
	public ShortURLMapping(long urlId, String longUrl, String shortenedUrl, long accessStats, LocalDateTime createdAt) {
		super();
		this.urlId = urlId;
		this.longUrl = longUrl;
		this.shortenedUrl = shortenedUrl;
		this.accessStats = accessStats;
		this.createdAt = createdAt;
	}

	// Getters and Setters
	public long getUrlId() {
		return urlId;
	}

	public void setUrlId(long urlId) {
		this.urlId = urlId;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortenedUrl() {
		return shortenedUrl;
	}

	public void setShortenedUrl(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}

	public long getAccessStats() {
		return accessStats;
	}

	public void setAccessStats(long accessStats) {
		this.accessStats = accessStats;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	// toString Method
	@Override
	public String toString() {
		return "ShortenedURL [urlId=" + urlId + ", longUrl=" + longUrl + ", shortenedUrl=" + shortenedUrl
				+ ", accessStats=" + accessStats + ", createdAt=" + createdAt + "]";
	}
		
}
