package com.fdmgroup.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.springboot.Model.Movie;
import com.fdmgroup.springboot.Model.Review;
import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.UserRepository;
import com.fdmgroup.springboot.Service.MovieService;
import com.fdmgroup.springboot.Service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MovieService movieService;
	
	
	
	
	@PostMapping("/movie/{title}")
	public String addReview(@PathVariable String title, @ModelAttribute("review") Review review, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Movie movie = movieService.getMovie(title);
		
		review.setMovie(movie);
		review.setUser(user);
		
		reviewService.addReview(review, movie);
		
		return "redirect:/movie/{title}";
	}
	
	@GetMapping("/clicklike/{id}")
	public String clickLike(@PathVariable int id, HttpSession session, @ModelAttribute Review review, @RequestParam("title") String movieTitle) {
		User user = (User) session.getAttribute("user");

		reviewService.clickLike(id, user);
		
		return "redirect:/movie/" + movieTitle + "#reviews";
	}
	
	@GetMapping("/clickdislike/{id}")
	public String clickDislike(@PathVariable int id, HttpSession session, @RequestParam("title") String movieTitle) {
		User user = (User) session.getAttribute("user");
		
		reviewService.clickDislike(id, user);
		
		return "redirect:/movie/" + movieTitle + "#reviews";
	}
	
	@GetMapping("/clickhaha/{id}")
	public String clickHaha(@PathVariable int id, HttpSession session, @RequestParam("title") String movieTitle) {
		User user = (User) session.getAttribute("user");
		
		reviewService.clickHaha(id, user);
		
		return "redirect:/movie/" + movieTitle + "#reviews";
	}
	
	@PostMapping("/editreview/{id}")
	public String editReview(@PathVariable int id, @ModelAttribute Review editreview) {
		reviewService.editReview(id, editreview.getReviewContent(), editreview.getRating());
		
		return "redirect:/mainpage";
	}
	
	
	
	
	
	
}


