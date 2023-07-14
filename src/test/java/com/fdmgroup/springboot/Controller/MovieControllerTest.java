package com.fdmgroup.springboot.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Service.MovieService;

@WebMvcTest(MovieController.class)
class MovieControllerTest {	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	MovieController movieController;
	
	@MockBean
	MovieService mockMovieService;
	
	@Mock
	MockHttpSession mockSession;
	
	@Mock
	Model mockModel;
	
	
	

	@Test
	void GET_add_movie_page() throws Exception {
		mockMvc.perform(get("/addmovie"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("movie"))
		.andExpect(view().name("addmovie"));
	}
	
	@Test
	void POST_add_movie() throws Exception {
		User user = new User("test", "password");
		Movie movie = new Movie("title", 2016,  "genre", 9);
		
		when(mockMovieService.addMovie(movie)).thenReturn("Success");
		
		assertEquals("addmovie", movieController.addMovie(movie, user, mockSession, mockModel));
	}
	

	
	
	
	
	
	
	
	
	
	
	
	

}
