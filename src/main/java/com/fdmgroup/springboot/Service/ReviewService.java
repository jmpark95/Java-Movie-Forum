package com.fdmgroup.springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.Review;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.MovieRepository;
import com.fdmgroup.springboot.Repository.ReviewRepository;
import com.fdmgroup.springboot.Repository.UserRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	
	
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
	
	public void clickLike(int id, User user) {
		Review review = reviewRepository.findById(id).get();
		
	    User managedUser = userRepository.findById(user.getUsername()).get();
		
		boolean doesListAlreadyContainUser = review.getLikedBy().contains(managedUser);
				
		if (doesListAlreadyContainUser) {
			List<User> currentList = review.getLikedBy();
			currentList.remove(managedUser);
			
			review.setLikedBy(currentList);
			
			reviewRepository.save(review);
		} else {
			List<User> currentList = review.getLikedBy();
			currentList.add(managedUser);
			
			review.setLikedBy(currentList);
			
			reviewRepository.save(review);
		}
	}
	
	public void clickDislike(int id, User user) {
		Review review = reviewRepository.findById(id).get();
		
	    User managedUser = userRepository.findById(user.getUsername()).get();
		
		boolean doesListAlreadyContainUser = review.getDislikedBy().contains(managedUser);
		
		if (doesListAlreadyContainUser) {
			List<User> currentList = review.getDislikedBy();
			currentList.remove(managedUser);
			
			review.setDislikedBy(currentList);
			
			reviewRepository.save(review);
		} else {
			List<User> currentList = review.getDislikedBy();
			currentList.add(managedUser);
			
			review.setDislikedBy(currentList);
			
			reviewRepository.save(review);
		}
	}
	
	public void clickHaha(int id, User user) {
		Review review = reviewRepository.findById(id).get();
		
	    User managedUser = userRepository.findById(user.getUsername()).get();
		
		boolean doesListAlreadyContainUser = review.getHahadBy().contains(managedUser);
		
		if (doesListAlreadyContainUser) {
			List<User> currentList = review.getHahadBy();
			currentList.remove(managedUser);
			
			review.setHahadBy(currentList);
			
			reviewRepository.save(review);
		} else {
			List<User> currentList = review.getHahadBy();
			currentList.add(managedUser);
			
			review.setHahadBy(currentList);
			
			reviewRepository.save(review);
		}
	}
	
	public void editReview(int reviewId, String updatedReviewContent, double updatedRating) {
		Review review = reviewRepository.findById(reviewId).get();
		
		review.setReviewContent(updatedReviewContent);
		review.setRating(updatedRating);
		
		reviewRepository.save(review);
	}
	
	
	
	
	
	
	
	
}
