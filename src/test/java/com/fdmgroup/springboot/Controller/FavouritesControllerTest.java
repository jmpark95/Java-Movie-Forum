package com.fdmgroup.springboot.Controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.fdmgroup.springboot.Service.UserService;

@SpringBootTest
class FavouritesControllerTest {
	@Autowired
	FavouritesController favouritesController;
	
	@MockBean
	UserService mockUserService;

	@Mock
	MockHttpSession mockSession;
	
	@Mock
	Model mockModel;


	
	
	@Test
	void get_users_favourite_movies() throws Exception {
		favouritesController.getFavouritesByUsername(mockSession, mockModel);
		
		verify(mockUserService, times(1)).getFavouritesByUsername(mockSession);
		
		assertEquals("favourites", favouritesController.getFavouritesByUsername(mockSession, mockModel));
	}

}
