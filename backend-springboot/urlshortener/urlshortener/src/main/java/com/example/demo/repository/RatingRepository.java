package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Ratings;

public interface RatingRepository extends JpaRepository<Ratings, Long>{

}
