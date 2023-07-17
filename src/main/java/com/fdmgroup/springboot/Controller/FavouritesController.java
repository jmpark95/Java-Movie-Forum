package com.fdmgroup.springboot.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Service.MovieService;
import com.fdmgroup.springboot.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FavouritesController {
	@Autowired
	UserService userService;
	
	@Autowired
	MovieService movieService;
	
	@GetMapping("/favourites")
	public String getFavourites(HttpSession session, Model model) {
		User sessionUser = (User) session.getAttribute("user");

		List<Movie> favouritesList = userService.getFavourites(sessionUser.getUsername());
	
		model.addAttribute("favouriteList", favouritesList);
		
		return "favourites";
	}
	
	@PostMapping("/favourites")
	public String addFavourite(@ModelAttribute Movie movie, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
				
		movieService.addFavourite(movie, sessionUser);
		
		return "redirect:/mainpage";
	}
	
	@GetMapping("/favourites/delete")
	public String deleteFavourite(HttpSession session, @RequestParam String movieTitle) {
		User sessionUser = (User) session.getAttribute("user");
		
		movieService.deleteUserFromFavourited(sessionUser.getUsername(), movieTitle);

		return "redirect:/favourites";
	}
	
}

