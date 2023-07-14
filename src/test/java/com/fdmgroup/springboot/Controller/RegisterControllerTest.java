package com.fdmgroup.springboot.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.UserRepository;
import com.fdmgroup.springboot.Service.UserService;

import jakarta.servlet.http.HttpSession;

@WebMvcTest(RegisterController.class)
class RegisterControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	RegisterController registerController;
	@MockBean
	UserService userService;
	@MockBean
	UserRepository userRepository;
	@Mock
	Model model;
	@Mock
	HttpSession session;
	
	

	@Test
	void GET_register_page() throws Exception {
		mockMvc.perform(get("/register"))
		.andExpect(status().isOk())
		.andExpect(view().name("register"));
	}
	
	@Test
	void POST_success_register_user() throws Exception {
		User user = new User("test", "password");
		
		when(userService.getUser(user.getUsername())).thenReturn(null);
		
		assertEquals("redirect:/mainpage", registerController.registerUser(user, model, session));
	}
	
	@Test
	void POST_fail_username_already_exists() throws Exception {
		User user = new User("test", "password");
		
		when(userService.getUser(user.getUsername())).thenReturn(user);
		
		assertEquals("register", registerController.registerUser(user, model, session));
	}
}
