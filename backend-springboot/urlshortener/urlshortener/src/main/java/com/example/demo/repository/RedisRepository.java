package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public void save(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
}
