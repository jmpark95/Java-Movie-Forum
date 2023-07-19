package com.fdmgroup.springboot.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.UserRepository;
import com.fdmgroup.springboot.Service.MovieService;
import com.fdmgroup.springboot.Service.ReviewService;

@SpringBootTest
class MovieControllerTest {	
	@Autowired
	MovieController movieController;
	
	@MockBean
	ReviewService mockReviewService;
	
	@MockBean
	UserRepository mockUserRepository;
	
	@MockBean
	MovieService mockMovieService;
	
	@Mock
	MockHttpSession mockSession;
	
	@Mock
	Model mockModel;
	
	
	
	
	
	@Test
	void GET_main_page() {
		movieController.getMainPage(mockModel);
		
		verify(mockMovieService, times(1)).getAllMovies();
	}
	
	@Test
	void GET_add_movie_page() throws Exception {
		assertEquals("addmovie", movieController.getAddMoviePage(mockModel));
	}
	
	@Test
	void POST_add_movie_success() {
		Movie movie = new Movie("title", 2016, "genre", 9);
	
		movieController.addMovie(movie, mockModel);
		
		verify(mockMovieService, times(1)).addMovie(movie);
		
		assertEquals("addmovie", movieController.addMovie(movie, mockModel));
	}
	
	@Test
	void GET_single_movie_page() {
		User mockUser = new User("test", "test");
		
		when(mockSession.getAttribute("user")).thenReturn(mockUser);
		when(mockUserRepository.findById("test")).thenReturn(Optional.of(mockUser));
		
		movieController.getSingleMoviePage("Avatar", mockModel, mockSession);
		
		verify(mockMovieService).getMovie("Avatar");
		
		verify(mockReviewService).getReviewsByMovie("Avatar");
		
		assertEquals("singlemovie", movieController.getSingleMoviePage("Avatar", mockModel, mockSession));
	}
	

	
	
//	@GetMapping("/movie/{title}")
//	public String getSingleMoviePage(@PathVariable String title, Model model, HttpSession session) {
//		Movie result = movieService.getMovie(title);
//		List<Review> allReviews = reviewService.getReviewsByMovie(title);
//		User sessionUser = (User) session.getAttribute("user");
//		User currentUser = userRepository.findById(sessionUser.getUsername()).get();
//		
//		model.addAttribute("movie", result);
//		model.addAttribute("reviews", allReviews);
//		model.addAttribute("review", new Review());
//		
//		model.addAttribute("currentUser", currentUser.getUsername());
//
//		return "singlemovie";
//	}
	
	
	
	
	
	
	
	
}
