package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Ratings;
import com.example.demo.service.RatingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController {
	
	private RatingService ratingService;
	
	public RatingController(RatingService ratingService) {
		super();
		this.ratingService = ratingService;
	}

	@PostMapping("/rateus")
	public Ratings addRating(@RequestBody int ratingNum) {
		return ratingService.addRating(ratingNum);
	}
	
	
}
