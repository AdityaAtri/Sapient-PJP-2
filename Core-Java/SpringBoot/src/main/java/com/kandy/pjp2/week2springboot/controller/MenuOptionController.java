package com.kandy.pjp2.week2springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuOptionController {
	
	@GetMapping("/index")
	public String showIndexPage() {
		return "index";
	}
}
