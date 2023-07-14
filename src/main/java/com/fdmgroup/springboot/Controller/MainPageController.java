package com.fdmgroup.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
	
	@GetMapping("/mainpage")
	public String getMainPage() {
		return "mainpage";
	}
}
