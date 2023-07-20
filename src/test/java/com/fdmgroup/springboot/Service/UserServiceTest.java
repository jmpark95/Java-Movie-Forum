package com.fdmgroup.springboot.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		movieRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	void add_user() {
		User user = new User("AddingUser", "AddingUserPW");
		
		User result = userService.addUser(user);

		assertEquals(user.toString(), result.toString());
	}
	
	@Test
	void get_user() {
		User user = new User("GetUser", "GetUserPW");
		userRepository.save(user);
		
		User foundUser = userService.getUser("GetUser");
		
		assertEquals(user.toString(), foundUser.toString());
	}
	
	@Test
	void get_user_no_match_returns_null() {
		User user = new User("NoMatchUser", "NoMatchUserPW");
		userRepository.save(user);

		assertNull(userService.getUser("wrongString"));
	}
	
	@Test
	void update_user() {
		User user = new User("User", "UserPW");
		userRepository.save(user);
		
		User updatedUser = userRepository.findById("User").get();
		
		updatedUser.setPassword("UpdatedPW");
		
		userService.updateUser(updatedUser);
		
		assertEquals(updatedUser, userRepository.findById("User").get());
	}
	
	@Test 
	void delete_user() {
		User user = new User("DeleteUser", "DeleteUserPW");
		userRepository.save(user);
		
		userService.deleteUser("DeleteUser");
		
		assertEquals(Optional.empty(), userRepository.findById("DeleteUser"));
	}
	
	@Test
	void get_favourites() {
		User user = new User("Min", "password");
		
		List<Movie> favouriteMovieList = new ArrayList<>();
		favouriteMovieList.add(new Movie("Avatar", 2016, "genre", 9));
		favouriteMovieList.add(new Movie("LOTR", 2013, "genre", 8));
		favouriteMovieList.add(new Movie("Django", 2010, "genre", 4));
		
		user.setFavourites(favouriteMovieList);
		
		userRepository.save(user);
		
		assertEquals(favouriteMovieList.toString(), userService.getFavourites("Min").toString());
	}
	
	@Test
	void get_watchlist() {
		User user = new User("Min", "password");
		
		List<Movie> watchList = new ArrayList<>();
		watchList.add(new Movie("Avatar", 2016, "genre", 9));
		watchList.add(new Movie("LOTR", 2013, "genre", 8));
		watchList.add(new Movie("Django", 2010, "genre", 4));
		
		user.setWatchlist(watchList);
		
		userRepository.save(user);
		
		assertEquals(watchList.toString(), userService.getWatchList("Min").toString());
	}
	
	@Test
	void add_user_to_following_list() {
		User user = new User("Min", "password");
		User userToFollow = new User("MinWantsToFollowThisUser", "password");
		
		userRepository.save(user);
		userRepository.save(userToFollow);
		
		userService.addFollowing("Min", userToFollow);
		
		assertEquals(userRepository.findById("MinWantsToFollowThisUser").get().toString(), userRepository.findById("Min").get().getFollowing().get(0).toString());
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}