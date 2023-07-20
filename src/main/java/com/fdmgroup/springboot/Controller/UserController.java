package com.fdmgroup.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fdmgroup.springboot.Model.User;
import com.fdmgroup.springboot.Repository.UserRepository;
import com.fdmgroup.springboot.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	
	
	@GetMapping("/profile/{username}")
	public String getProfilePage(@PathVariable String username, Model model, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		User user = userService.getUser(username);
		List<User> allUsers = userRepository.findAll();
		User sessionUserDetails = userRepository.findById(sessionUser.getUsername()).get();
		
		
		model.addAttribute("userprofile", user);
		model.addAttribute("usersname", user.getUsername());
		model.addAttribute("allusers", allUsers);
		model.addAttribute("sessionuserdetails", sessionUserDetails.getNamesOfFollowing());
		model.addAttribute("sessionusername", sessionUser.getUsername());
		
		System.out.println(model);

		
		return "profile";	
	}
	
	@GetMapping("/follow/{userToAdd}")
	public String addToFollowing(HttpSession session, @PathVariable String userToAdd) {
		User sessionUser = (User) session.getAttribute("user");
		String username = sessionUser.getUsername();
		
		User userWantToFollow = userRepository.findById(userToAdd).get();
		
		userService.addFollowing(username, userWantToFollow);
		
		return "redirect:/profile/" + username;
	}
	
	
	
	
}
