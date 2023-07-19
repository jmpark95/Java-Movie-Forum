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
import com.fdmgroup.springboot.Model.Review;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.UserRepository;
import com.fdmgroup.springboot.Service.MovieService;
import com.fdmgroup.springboot.Service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MovieController {
	@Autowired
	MovieService movieService;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	UserRepository userRepository;
	
	
	
	
	
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
	public String getSingleMoviePage(@PathVariable String title, Model model, HttpSession session) {
		Movie result = movieService.getMovie(title);
		List<Review> allReviews = reviewService.getReviewsByMovie(title);
		User sessionUser = (User) session.getAttribute("user");
		User currentUser = userRepository.findById(sessionUser.getUsername()).get();
		
		model.addAttribute("movie", result);
		model.addAttribute("reviews", allReviews);
		model.addAttribute("review", new Review());
		
		model.addAttribute("currentUser", currentUser.getUsername());

		return "singlemovie";
	}
}
