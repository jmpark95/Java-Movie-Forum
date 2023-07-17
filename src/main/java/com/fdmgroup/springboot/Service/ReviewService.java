package com.fdmgroup.springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.Review;
import com.fdmgroup.springboot.Repository.MovieRepository;
import com.fdmgroup.springboot.Repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	MovieRepository movieRepository;
	
	//Create
	public Review addReview(Review review, Movie movie) {
		movie.setRating(review.getRating());
		
		movieRepository.save(movie);
		
		return reviewRepository.save(review);
	}
	
	//Read
	public List<Review> getReviewsByMovie(String movieTitle) {
		return reviewRepository.findByMovieTitle(movieTitle);
	}
}
