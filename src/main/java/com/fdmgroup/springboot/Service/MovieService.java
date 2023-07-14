package com.fdmgroup.springboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Repository.MovieRepository;

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
}
