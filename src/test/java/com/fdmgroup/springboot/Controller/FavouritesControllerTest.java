package com.fdmgroup.springboot.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
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

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class FavouritesControllerTest {
	@Autowired
	FavouritesController favouritesController;
	
	@MockBean
	UserService mockUserService;
	
	@MockBean
	MovieService mockMovieService;

	@Mock
	MockHttpSession mockSession;
	
	@Mock
	Model mockModel;
	
	User mockUser;
	
	
	
	
	
	@BeforeEach
	void setup() {
		mockUser = new User();
	}

	
	@Test
	void GET_favourites() {		
		when(mockSession.getAttribute("user")).thenReturn(mockUser);
		
		favouritesController.getFavourites(mockSession, mockModel);
		
		verify(mockUserService, times(1)).getFavourites(mockUser.getUsername());

		assertEquals("favourites",  favouritesController.getFavourites(mockSession, mockModel));
	}
	
	@Test
	void POST_add_favourite() {
		Movie movie = new Movie("title", 2016, "genre", 9);
		
		when(mockSession.getAttribute("user")).thenReturn(mockUser);
		when(mockMovieService.getMovie("title")).thenReturn(movie);

		assertEquals("redirect:/mainpage", favouritesController.addFavourite("title", mockSession));
		
		verify(mockMovieService, times(1)).addFavourite(movie, mockUser);
	}
	
	@Test
	void DELETE_favourite() {		
		when(mockSession.getAttribute("user")).thenReturn(mockUser);
		
		favouritesController.deleteFavourite(mockSession, "Avatar");
		
		verify(mockMovieService, times(1)).deleteUserFromFavourited(mockUser.getUsername(), "Avatar");
		
		assertEquals("redirect:/favourites", favouritesController.deleteFavourite(mockSession, "Avatar"));
	}

}
