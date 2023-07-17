package com.fdmgroup.springboot.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.MovieRepository;
import com.fdmgroup.springboot.Repository.UserRepository;

import jakarta.transaction.Transactional;


@SpringBootTest
@Transactional
class MovieServiceTest {
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieService movieService;
	
	@Autowired
	UserRepository userRepository;
	

	

	@BeforeEach
	void setup() {
		movieRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	void add_movie_success() {
		Movie movie = new Movie("Title", 2023, "Action", 9);
		
		Movie result = movieService.addMovie(movie);

		assertEquals(movie.toString(), result.toString());
	}
	
	@Test
	void add_movie_returns_null_if_already_exists_in_database() {
		Movie movie = new Movie("Title", 2023, "Action", 9);
		movieRepository.save(movie);
		
		Movie result = movieService.addMovie(movie);

		assertNull(result);
	}
	
	@Test
	void get_movie() {
		Movie movie = new Movie("Title", 2023, "Action", 9);
		movieRepository.save(movie);
		
		Movie foundMovie = movieService.getMovie("Title");
		
		assertEquals(movie.toString(), foundMovie.toString());
	}
	
	@Test
	void get_movie_no_match_returns_null() {
		Movie movie = new Movie("Title", 2023, "Action", 9);
		movieRepository.save(movie);
		
		assertNull(movieService.getMovie("wrongTitle"));
	}
	
	@Test
	void update_Movie() {
		Movie movie = new Movie("Title", 2023, "Action", 9);
		movieRepository.save(movie);
		
		Movie updatedMovie = movieRepository.findById("Title").get();
		
		updatedMovie.setGenre("Romance");
		
		movieService.updateMovie(updatedMovie);
		
		assertEquals(updatedMovie, movieRepository.findById("Title").get());
	}
	
	@Test 
	void delete_Movie() {
		Movie movie = new Movie("Title", 2023, "Action", 9);
		movieRepository.save(movie);
		
		movieService.deleteMovie("Title");
		
		assertEquals(Optional.empty(), movieRepository.findById("Title"));
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
	
	@Test
	void add_favourite() {
		Movie movie = new Movie("title", 2016, "genre", 9);
		User user = new User("Min", "min");
		
		userRepository.save(user);
		movieRepository.save(movie);
		
		movieService.addFavourite(movie, user);
		
		Movie updatedMovie = movieRepository.findById("title").get();
		
		List<User> testList = new ArrayList<>();
		testList.add(user);
		
		assertEquals(testList.toString(), updatedMovie.getFavouritedBy().toString());
		
	}
	
	@Test
	void add_watchlist() {
		Movie movie = new Movie("title", 2016, "genre", 9);
		User user = new User("Min", "min");
		
		userRepository.save(user);
		movieRepository.save(movie);
		
		movieService.addWatchlist(movie, user);
		
		Movie updatedMovie = movieRepository.findById("title").get();
		
		List<User> testList = new ArrayList<>();
		testList.add(user);
		
		assertEquals(testList.toString(), updatedMovie.getWatchlistedBy().toString());
		
	}
	
	@Test
	void delete_a_user_from_a_movies_favouritedBy_list() {
		User user = new User("TestforFav", "abc123");
		User user2 = new User("TestforFav2", "abc123");
		Movie movie = new Movie("MovieForFav", 2016, "genre", 9);
		
		List<User> favList = new ArrayList<>();
		favList.add(user);
		favList.add(user2);
		
		movie.setFavouritedBy(favList);
		
		movieRepository.save(movie);
		
		movieService.deleteUserFromFavourited("TestforFav2", "MovieForFav");
		
		List<User> testFavList = new ArrayList<>();
		testFavList.add(user);
		
		assertEquals(testFavList.toString(), movieRepository.findById("MovieForFav").get().getFavouritedBy().toString());
	}
	
	@Test
	void delete_a_user_from_a_movies_watchlistedBy_list() {
		User user = new User("TestforFav", "abc123");
		User user2 = new User("TestforFav2", "abc123");
		Movie movie = new Movie("MovieForFav", 2016, "genre", 9);
		
		List<User> watchlist = new ArrayList<>();
		watchlist.add(user);
		watchlist.add(user2);
		
		movie.setWatchlistedBy(watchlist);
		
		movieRepository.save(movie);
		
		movieService.deleteUserFromWatchlisted("TestforFav2", "MovieForFav");
		
		List<User> testWatchlist = new ArrayList<>();
		testWatchlist.add(user);
		
		assertEquals(testWatchlist.toString(), movieRepository.findById("MovieForFav").get().getWatchlistedBy().toString());
	}
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Test
//	void test() {
//		Movie movie = new Movie("TESTTTTT", 2016, "genre", 9);
//		Movie movie2 = new Movie("test2", 2015, "action", 9);
//		
//		Movie Movie = new Movie("test1","testpw");
//		
//		movie.setCreatedBy(Movie);
//		movie2.setCreatedBy(Movie);
//		
//		movieRepository.save(movie);
//		movieRepository.save(movie2);
//		
//		movieRepository.deleteById("TESTTTTT");
//	}

	

	
	
//	@Test
//	void add__movie_returns_success() {
//        Movie movie = new Movie("movieTitle", 2016, "genre", 9);
//
//        assertEquals("Success", movieService.addMovie(movie));
//	}
//	
//	@Test
//	void already_existing_movie_returns_fail() {
//        Movie movie = new Movie("movieTitle", 2016, "genre", 9);
//        
//        movieService.addMovie(movie);
//
//        assertEquals("Fail", movieService.addMovie(movie));
//	}
//	

//	
//	@Test
//	void get_single_movie() {
//		Movie movie = new Movie("title", 2016, "genre", 9);
//		movieRepository.save(movie);
//
//		assertEquals(movie, movieService.getSingleMovie("title"));
//	}
//	
	
	
	
	
	
	
	
	

}
