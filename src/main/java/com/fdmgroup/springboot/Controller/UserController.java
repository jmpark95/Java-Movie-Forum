package com.fdmgroup.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/favourites")
	public String getFavouritesByUsername(HttpSession session, Model model) {
		List<Movie> favouritesList = userService.getFavouritesByUsername(session);
		model.addAttribute("favouriteList", favouritesList);
		
		return "favourites";
	}
}
