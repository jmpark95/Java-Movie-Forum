package com.fdmgroup.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.UserRepository;
import com.fdmgroup.springboot.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("error", "none");
	
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(User user, Model model, HttpSession session) {
		if (userService.getUser(user.getUsername()) != null) {
			model.addAttribute("error", "username already exists");
			
			return "register";
		} else {
			userRepository.save(user);
			session.setAttribute("user", user);
			
			return "redirect:/mainpage";
		}
	}
}
