package com.fdmgroup.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("error", "none");
		
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(User user, Model model, HttpSession session) {
		User checkUser = userService.getUser(user.getUsername());
		
		if (checkUser == null) {
			model.addAttribute("error", "username doesnt exist");
			return "login";
		} 
		else if (!user.getPassword().equals(checkUser.getPassword())) {
			model.addAttribute("error", "wrong password");
			return "login";
		}
		else {
			session.setAttribute("user", checkUser);
			return "redirect:/mainpage";
		}
	}
	
}