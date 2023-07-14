package com.fdmgroup.springboot.Service;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Repository.MovieRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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
	
	@Test
	void get_all_movies() {
		List<Movie> testMovieList = new ArrayList<>();
		testMovieList.add(new Movie("title", 2016, "genre", 9));
		testMovieList.add(new Movie("title2", 2013, "genre", 8));
		testMovieList.add(new Movie("title3", 2010, "genre", 4));
		
		movieRepository.saveAll(testMovieList);
	
	    assertEquals(3, movieService.getAllMovies().size());
	    assertEquals(testMovieList.toString(), movieService.getAllMovies().toString());
	}
	

}
