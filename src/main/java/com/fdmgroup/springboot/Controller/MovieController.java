package com.fdmgroup.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Service.MovieService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MovieController {
	@Autowired
	MovieService movieService;
	
	@GetMapping("/mainpage")
	public String getMainPage(Model model) {
		List<Movie> allMovies = movieService.getAllMovies();
		model.addAttribute("movies", allMovies);
		
		return "mainpage";
	}
	
	@GetMapping("/addmovie")
	public String getAddMoviePage(Model model) {
		model.addAttribute("movie", new Movie());
		
		return "addmovie";
	}
	
	@PostMapping("/addmovie")
	public String addMovie(@ModelAttribute Movie movie, Model model) {		
		//user = (User) session.getAttribute("user");		
		
		Movie result = movieService.addMovie(movie);
		
		if (result != null) {
			model.addAttribute("message", "success");	
			model.addAttribute("movie", new Movie());
		} else {
			model.addAttribute("message", "fail");
			model.addAttribute("movie", new Movie());
		}

		return "addmovie";
	}
	
	@GetMapping("/movie/{title}")
	public String getSingleMoviePage(@PathVariable String title, Model model) {
		Movie result = movieService.getMovie(title);
		model.addAttribute("movie", result);
		
		return "singlemovie";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	//Might need this later if need User and user sesesion
//	@PostMapping("/addmovie")
//	public String addMovie(@ModelAttribute Movie movie, User user, HttpSession session, Model model) {		
//		//user = (User) session.getAttribute("user");		
//		
//		Movie result = movieService.addMovie(movie);
//		
//		if (result != null) {
//			model.addAttribute("message", "success");	
//			model.addAttribute("movie", new Movie());
//		} else {
//			model.addAttribute("message", "fail");
//			model.addAttribute("movie", new Movie());
//		}
//
//		return "addmovie";
//	}
	
//	@DeleteMapping("/movie/{title}")
//	public void delete
	
	
	
	//
	

	
}
