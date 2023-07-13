package com.fdmgroup.springboot.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.UserRepository;

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
	
	
}
