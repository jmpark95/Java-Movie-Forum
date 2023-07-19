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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Service.MovieService;
import com.fdmgroup.springboot.Service.UserService;

import jakarta.servlet.http.HttpSession;

@SpringBootTest
class WatchlistControllerTest {
	@Autowired
	WatchlistController watchlistController;
	
	@MockBean
	UserService mockUserService;
	
	@MockBean
	MovieService mockMovieService;

	@Mock
	MockHttpSession mockSession;
	
	@Mock
	Model mockModel;
	
	@Mock
	User mockUser;
	
	
		
	
	
	@Test
	void get_watchlist() {
		when(mockSession.getAttribute("user")).thenReturn(mockUser);

		watchlistController.getWatchlist(mockSession, mockModel);
		
		verify(mockUserService, times(1)).getWatchList(mockUser.getUsername());
		
		assertEquals("watchlist", watchlistController.getWatchlist(mockSession, mockModel));
	}
	
	@Test
	void add_to_watchlist() {
		Movie movie = new Movie();
		when(mockSession.getAttribute("user")).thenReturn(mockUser);
		when(mockMovieService.getMovie("Avatar")).thenReturn(movie);
		
		watchlistController.addWatchlist("Avatar", mockSession);
		
		verify(mockMovieService, times(1)).getMovie("Avatar");
		
		verify(mockMovieService, times(1)).addMovieToWatchlist(movie, mockUser);
		
		assertEquals("redirect:/mainpage", watchlistController.addWatchlist("Avatar", mockSession));
	}
	
	@Test
	void delete_watchlist() {
		when(mockSession.getAttribute("user")).thenReturn(mockUser);
		
		watchlistController.deleteWatchlist(mockSession, "Avatar");
		
		verify(mockMovieService, times(1)).deleteUserFromWatchlisted(mockUser.getUsername(), "Avatar");
		
		assertEquals("redirect:/watchlist", watchlistController.deleteWatchlist(mockSession, "Avatar"));


	}


}

