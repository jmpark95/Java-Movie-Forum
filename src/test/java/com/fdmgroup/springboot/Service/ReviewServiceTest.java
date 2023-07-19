package com.fdmgroup.springboot.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	void get_all_reviews_for_a_movie_by_its_title() {
		Review review = new Review("Great", 9);
		Review review2 = new Review("bad", 3);
		User user = new User("Reviewtest", "min");
		User user2 = new User("Reviewtest2", "don");
		Movie movie = new Movie("Titanic", 1930, "Drama", 9);

		review.setMovie(movie);
		review.setUser(user);
		review2.setMovie(movie);
		review2.setUser(user2);
		
		List<Review> reviewList = new ArrayList<>();
		reviewList.add(review);
		reviewList.add(review2);
		
		reviewRepository.saveAll(reviewList);

		assertEquals(reviewList, reviewService.getReviewsByMovie("Titanic"));
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
	void editing_existing_review() {
		Review oldReview = new Review("this is the old comment", 4.0);
		
		Review savedReview = reviewRepository.save(oldReview);

		reviewService.editReview(savedReview.getId(), "this is the new comment", 9.0);
		
		Review updatedReview = reviewRepository.findById(savedReview.getId()).get();
		
		assertEquals("this is the new comment", updatedReview.getReviewContent());
		assertEquals(9.0, updatedReview.getRating());

	}
	
	
	
	
	
	
	

}

