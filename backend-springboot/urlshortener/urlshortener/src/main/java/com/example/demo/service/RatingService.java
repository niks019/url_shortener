package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Ratings;
import com.example.demo.repository.RatingRepository;

@Service
public class RatingService {
	private RatingRepository ratingRepository;

	public RatingService(RatingRepository ratingRepository) {
		super();
		this.ratingRepository = ratingRepository;
	}
	
	public Ratings addRating(int ratingNum) {

        Ratings ratings = new Ratings();
        ratings.setUsername("dummy");
        ratings.setStars(ratingNum);
        return ratingRepository.save(ratings);
    }
	
}
