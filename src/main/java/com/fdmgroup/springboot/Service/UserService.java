package com.fdmgroup.springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.MovieRepository;
import com.fdmgroup.springboot.Repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	
	
	

	// Create
	public User addUser(User user) {
		User savedUser = userRepository.save(user);

		return savedUser;
	}

	// Read
	public User getUser(String userName) {
		Optional<User> user = userRepository.findById(userName);

		if (user.isPresent())
			return user.get();
		else
			return null;
	}

	// Update
	public User updateUser(User user) {
		User updatedUser = userRepository.save(user);

		return updatedUser;
	}

	// Delete
	public void deleteUser(String userName) {
		userRepository.deleteById(userName);
	}

	// Favorites for a user
	public List<Movie> getFavourites(String userName) {
		User user = userRepository.findById(userName).get();

		return user.getFavourites();
	}
	
	//Watchlist
	public List<Movie> getWatchList(String userName) {
		User user = userRepository.findById(userName).get();

		return user.getWatchlist();
	}

}
