package com.fdmgroup.springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fdmgroup.springboot.Model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r WHERE r.movie.title = ?1")
	List<Review> findByMovieTitle(String movieTitle);
}

