package com.fdmgroup.springboot.Service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.UserRepository;


@SpringBootTest
class UserServiceTest {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	
	@BeforeEach
	void setup() {
		userRepository.deleteAll();
	}

	@Test
	void get_user() {
		User user = new User("Test", "testpw");
		userRepository.save(user);
		User result = userService.getUser("Test");

		assertEquals(user.toString(), result.toString());
	}
	
	@Test
	void get_user_no_match() {
		User user = new User("Test", "testpw");
		userRepository.save(user);

		assertNull(userService.getUser("abc"));
	}
	
	
	
	
	
	
	
	

}