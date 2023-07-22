package com.fdmgroup.springboot.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.Review;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.MovieRepository;
import com.fdmgroup.springboot.Repository.ReviewRepository;
import com.fdmgroup.springboot.Repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class ReviewServiceTest {
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	UserRepository userRepository;
	


	
	
	
	@BeforeEach
	void setup() {
		reviewRepository.deleteAll();
		movieRepository.deleteAll();
	}
	
	
	public Review addReview(Review review, Movie movie) {
		movie.setRating(review.getRating());
		
		movieRepository.save(movie);
		
		return reviewRepository.save(review);
	}
	

	@Test
	void add_review() {
		Review review = new Review("Great movie", 10.0);
		Movie movie = new Movie("Titatnic", 1995, "Drama", 9);
		movie.setRating(review.getRating());
		
		movieRepository.save(movie);
		
		reviewService.addReview(review, movie);
		
		assertEquals(review.toString(), reviewService.addReview(review, movie).toString());	
	}
	
	@Test
	void click_like_test() {
		User user =  new User("test", "testpw");
		User user2 = new User("test2", "testpw");
		List<User> likedList = new ArrayList<>();
		likedList.add(user);
		likedList.add(user2);
		
		Review review = new Review();
		review.setLikedBy(likedList);
		
		Review savedReview = reviewRepository.save(review);

		reviewService.clickLike(savedReview.getId(), user2);
		
		List<User> testLikedList = new ArrayList<>();
		testLikedList.add(user);

		assertEquals(testLikedList.toString(), savedReview.getLikedBy().toString());
	}
	
	
	@Test
	void click_dislike_test() {
		User user =  new User("test", "testpw");
		User user2 = new User("test2", "testpw");
		List<User> dislikedList = new ArrayList<>();
		dislikedList.add(user);
		dislikedList.add(user2);
		
		Review review = new Review();
		review.setDislikedBy(dislikedList);
		
		Review savedReview = reviewRepository.save(review);

		reviewService.clickDislike(savedReview.getId(), user2);
		
		List<User> testdislikedList = new ArrayList<>();
		testdislikedList.add(user);

		assertEquals(testdislikedList.toString(), savedReview.getDislikedBy().toString());
	}
	
	@Test
	void click_haha_test() {
		User user =  new User("test", "testpw");
		User user2 = new User("test2", "testpw");
		List<User> hahaList = new ArrayList<>();
		hahaList.add(user);
		hahaList.add(user2);
		
		Review review = new Review();
		review.setHahadBy(hahaList);
		
		Review savedReview = reviewRepository.save(review);

		reviewService.clickHaha(savedReview.getId(), user2);
		
		List<User> testHahaList = new ArrayList<>();
		testHahaList.add(user);

		assertEquals(testHahaList.toString(), savedReview.getHahadBy().toString());
	}
	
	
	
	
	
	@Test
	void editing_existing_review() {
		Review oldReview = new Review("this is the old comment", 4.0);
		
		Review savedReview = reviewRepository.save(oldReview);

		reviewService.editReview(savedReview.getId(), "this is the new comment", 9.0);
		
		Review updatedReview = reviewRepository.findById(savedReview.getId()).get();
		
		assertEquals("this is the new comment", updatedReview.getReviewContent());
		assertEquals(9.0, updatedReview.getRating());
	}
	
	@Test
	void delete_review() {
		Review review = new Review("review comment", 4.0);
		
		Review savedReview = reviewRepository.save(review);
		
		reviewService.deleteReview(savedReview.getId());
		
		assertTrue(reviewRepository.findById(savedReview.getId()).isEmpty());
	}
	
	
	
	
	
	
	

}

