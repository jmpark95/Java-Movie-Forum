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

	@Test
	void add_review() {
		Review review = new Review("Great movie", 10);
		Movie movie = new Movie("Titatnic", 1995, "Drama", 9);
		User user = new User("Testuser", "pw");
		
		review.setMovie(movie);
		review.setUser(user);
		
		Review returnedReview = reviewService.addReview(review);
		int tableID = returnedReview.getId();
		
		assertEquals(review, reviewRepository.findById(tableID).get());	
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

