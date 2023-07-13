package com.fdmgroup.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String getHomepage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("error", "none");
		
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(User user, Model model, HttpSession session) {
		User checkUser = userService.getUser(user.getUsername());
		
		//if username doesnt exist in the database
		if (checkUser == null) {
			model.addAttribute("error", "username doesnt exist");

			return "login";
		} 
		//wrong password
		else if (!user.getPassword().equals(checkUser.getPassword())) {
			model.addAttribute("error", "wrong password");
			return "login";
		}
		//only when user isn't null AND passwords match, allow onto mainPage
		else {
			session.setAttribute("user", checkUser);
			return "redirect:/mainpage";
		}
	}
	
}
