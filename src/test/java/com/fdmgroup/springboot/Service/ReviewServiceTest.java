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

@SpringBootTest
//@Transactional
class ReviewServiceTest {
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	MovieRepository movieRepository;
	


	
	
	
	@BeforeEach
	void setup() {
		reviewRepository.deleteAll();
		movieRepository.deleteAll();
	}
	
	
	//Create
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

	


	
	
	
	
	
	
	
	
	
	

}

