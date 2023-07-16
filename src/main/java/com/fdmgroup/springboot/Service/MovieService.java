package com.fdmgroup.springboot.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.MovieRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepository;
	
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


	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	


	

}
