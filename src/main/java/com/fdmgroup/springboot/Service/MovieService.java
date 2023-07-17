package com.fdmgroup.springboot.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.MovieRepository;
import com.fdmgroup.springboot.Repository.UserRepository;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	UserRepository userRepository;
	
	//Create
	public Movie addMovie(Movie movie) {
		boolean doesMovieAlreadyExist = movieRepository.existsByTitle(movie.getTitle());
		
		if (!doesMovieAlreadyExist) {
			movieRepository.save(movie);			
			return movie;
		} else {
			return null;
		}
	}
	
	//Read
	public Movie getMovie(String title) {
		Optional<Movie> movie = movieRepository.findById(title);
		
		if (movie.isPresent())
			return movie.get();
		else 
			return null;
	}
	
	//Update
	public Movie updateMovie(Movie movie) {
		Movie updatedMovie = movieRepository.save(movie);
		
		return updatedMovie;
	}
	
	//Delete
	public void deleteMovie(String title) {
		movieRepository.deleteById(title);
	}
	
	
	//List of all movies for main page
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	
	
	public void addFavourite(Movie queryMovie, User user) {
		Movie movie = movieRepository.findById(queryMovie.getTitle()).get();

		List<User> favouritedList = new ArrayList<>();

		for (User item : movie.getFavouritedBy()) {
			favouritedList.add(item);
		}

		favouritedList.add(user);

		movie.setFavouritedBy(favouritedList);

		movieRepository.save(movie);
	}
	
	public void addWatchlist(Movie queryMovie, User user) {
		Movie movie = movieRepository.findById(queryMovie.getTitle()).get();

		List<User> watchList = new ArrayList<>();

		for (User item : movie.getWatchlistedBy()) {
			watchList.add(item);
		}

		watchList.add(user);

		movie.setWatchlistedBy(watchList);

		movieRepository.save(movie);
	}


	//Delete a user from a movie's favouritedBy list
	public void deleteUserFromFavourited(String userName, String movieTitle) {		
		Movie movie = movieRepository.findById(movieTitle).get();
		User user = userRepository.findById(userName).get();

		movie.getFavouritedBy().remove(user);

		movieRepository.save(movie);
	}
	
	public void deleteUserFromWatchlisted(String userName, String movieTitle) {		
		Movie movie = movieRepository.findById(movieTitle).get();
		User user = userRepository.findById(userName).get();

		movie.getWatchlistedBy().remove(user);

		movieRepository.save(movie);
	}
	
	
	
	
	
	
	
	
	
	
	
	

	

	


	

}
