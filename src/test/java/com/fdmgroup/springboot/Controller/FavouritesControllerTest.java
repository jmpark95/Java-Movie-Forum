package com.fdmgroup.springboot.Controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Service.MovieService;
import com.fdmgroup.springboot.Service.UserService;

@SpringBootTest
class FavouritesControllerTest {
	@Autowired
	FavouritesController favouritesController;
	
	@MockBean
	UserService userService;
	
	@MockBean
	MovieService movieService;

	@Mock
	MockHttpSession mockSession;
	
	@Mock
	Model mockModel;
	
	
	
	@Test
	void POST_add_favourite() {
		Movie movie = new Movie("title", 2016, "genre", 9);
		User user = new User("test", "testpw");
		
		when(mockSession.getAttribute("user")).thenReturn(user);

		assertEquals("redirect:/mainpage", favouritesController.addFavourite(movie, mockSession));
		
		verify(movieService, times(1)).addFavourite(movie, user);
	}


	


}
