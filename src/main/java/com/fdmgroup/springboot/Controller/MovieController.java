package com.fdmgroup.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Service.MovieService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MovieController {
	@Autowired
	MovieService movieService;
	
	@GetMapping("/addmovie")
	public String getAddMoviePage(Model model) {
		model.addAttribute("movie", new Movie());
		
		return "addmovie";
	}
	
	@PostMapping("/addmovie")
	public String addMovie(@ModelAttribute Movie movie, User user, HttpSession session, Model model) {		
		user = (User) session.getAttribute("user");		
		movie.setCreatedBy(user);
		
		String result = movieService.addMovie(movie);
		
		if (result.equals("Success")) {
			model.addAttribute("message", "success");	
			model.addAttribute("movie", new Movie());
		} else {
			model.addAttribute("message", "fail");
			model.addAttribute("movie", new Movie());
		}

		return "addmovie";
	}
	
}
