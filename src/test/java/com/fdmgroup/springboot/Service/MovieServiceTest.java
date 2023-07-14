package com.fdmgroup.springboot.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Repository.MovieRepository;

@SpringBootTest
class MovieServiceTest {
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieService movieService;
	
	@BeforeEach
	void setup() {
		movieRepository.deleteAll();
	}

	
	
	@Test
	void add__movie_returns_success() {
        Movie movie = new Movie("movieTitle", 2016, "genre", 9);

        assertEquals("Success", movieService.addMovie(movie));
	}
	
	@Test
	void already_existing_movie_returns_fail() {
        Movie movie = new Movie("movieTitle", 2016, "genre", 9);
        
        movieService.addMovie(movie);

        assertEquals("Fail", movieService.addMovie(movie));
	}
	

}
