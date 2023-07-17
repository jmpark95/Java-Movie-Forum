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
public class WatchlistController {
	@Autowired
	UserService userService;
	
	@Autowired
	MovieService movieService;
	
	@GetMapping("/watchlist")
	public String getWatchlist(HttpSession session, Model model) {
		User sessionUser = (User) session.getAttribute("user");

		List<Movie> watchList = userService.getWatchList(sessionUser.getUsername());
	
		model.addAttribute("watchList", watchList);
		
		return "watchlist";
	}
	
	@PostMapping("/watchlist")
	public String addWatchList(@ModelAttribute Movie movie, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
				
		movieService.addWatchlist(movie, sessionUser);
		
		return "redirect:/mainpage";
	}
	
	@GetMapping("/watchlist/delete")
	public String deleteWatchlist(HttpSession session, @RequestParam String movieTitle) {
		User sessionUser = (User) session.getAttribute("user");
		
		movieService.deleteUserFromWatchlisted(sessionUser.getUsername(), movieTitle);

		return "redirect:/watchlist";
	}
	
}

