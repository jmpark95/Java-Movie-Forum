package com.fdmgroup.springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User getUser(String userName) {
		Optional<User> user = userRepository.findById(userName);
		
		if (user.isPresent())
			return user.get();
		else 
			return null;
	}
	
	public List<Movie> getFavouritesByUsername(HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");

		Optional<User> user = userRepository.findById(sessionUser.getUsername());
        
        if (user.isPresent()) {
        	return user.get().getFavourites();
        } else {
            return null;
        }
	}
	
	
}
