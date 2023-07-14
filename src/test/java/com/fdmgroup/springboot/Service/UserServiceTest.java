package com.fdmgroup.springboot.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.MovieRepository;
import com.fdmgroup.springboot.Repository.UserRepository;

import jakarta.transaction.Transactional;


@SpringBootTest
@Transactional
class UserServiceTest {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	UserService userService;
	
	@Mock
	MockHttpSession mockSession;
	
	
	@BeforeEach
	void setup() {
		userRepository.deleteAll();
		movieRepository.deleteAll();
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
	
	@Test
	void get_users_favourite_movies() {
		User user = new User("Test", "testpw");
		Movie movie1 = new Movie("movieTitle", 2016, "genre", 9);
		Movie movie2 = new Movie("movieTitle2", 2009, "genre", 7);
		Movie movie3 = new Movie("movieTitle3", 2013, "genre", 3);
		
		List<Movie> favouritesList = new ArrayList<>();
		favouritesList.add(movie1);
		favouritesList.add(movie2);
		favouritesList.add(movie3);
		
		when(mockSession.getAttribute("user")).thenReturn(user);
		
		movieRepository.saveAll(favouritesList);
		
		user.setFavourites(favouritesList);
		
		userRepository.save(user);
		
		assertEquals(favouritesList.toString(), userService.getFavouritesByUsername(mockSession).toString());
	}
	
	
	
	
	
	
	
	
	

}