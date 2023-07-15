package com.fdmgroup.springboot.Service;

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
	
	public String addMovie(Movie movie) {
		if (movieRepository.existsByTitle(movie.getTitle())) {
			return "Fail";
		} else {
			movieRepository.save(movie);
			return "Success";
		}
	}
	
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	
	public Movie getSingleMovie(String title) {
		Optional<Movie> movie = movieRepository.findById(title);
		
		if (movie.isPresent())
			return movie.get();
		else
			return null;
	}
	

}
