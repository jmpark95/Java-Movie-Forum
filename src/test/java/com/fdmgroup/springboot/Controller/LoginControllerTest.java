package com.fdmgroup.springboot.Controller;

import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

@WebMvcTest(LoginController.class)
class LoginControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	LoginController loginController;
	
	@MockBean
	UserService userService;
	
	@Mock
	Model mockModel;
	
	@Mock
	MockHttpSession mockSession;

	


  
	@Test
	public void GET_root_directory() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}

	@Test
	public void GET_login_page() throws Exception {
		mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("error"))
		.andExpect(view().name("login"));
	}
	
	@Test
	public void POST_successful_user_login() {
		User user = new User("testuser", "password");
		User mockUser = new User("testuser", "password");
		
		when(userService.getUser(user.getUsername())).thenReturn(mockUser);

		assertEquals("redirect:/mainpage", loginController.loginUser(user, mockModel, mockSession));
	}
	
	@Test
	public void POST_fail_username_doesnt_exist() {
		User user = new User("testuser", "password");
		
		when(userService.getUser(user.getUsername())).thenReturn(null);

		assertEquals("login", loginController.loginUser(user, mockModel, mockSession));
	}
	
	@Test
	public void POST_fail_wrong_password() {
		User user = new User("testuser", "password");
		User mockUser = new User("testuser", "wrongPassword");

		when(userService.getUser(user.getUsername())).thenReturn(mockUser);

		assertEquals("login", loginController.loginUser(user, mockModel, mockSession));
	}
	
	@Test
	public void GET_logout() throws Exception {
		loginController.logout(mockSession);
		
		mockMvc.perform(get("/logout"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"));

		verify(mockSession, times(1)).invalidate();
	}

}
