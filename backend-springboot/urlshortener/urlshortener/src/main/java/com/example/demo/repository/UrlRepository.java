package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ShortURLMapping;

public interface UrlRepository extends JpaRepository<ShortURLMapping, Long> {
    Optional<ShortURLMapping> findByShortenedUrl(String shortenedUrl);
    Optional<ShortURLMapping> findByLongUrl(String longUrl);
    Optional<ShortURLMapping> findByUrlId(Long urlId);
}
