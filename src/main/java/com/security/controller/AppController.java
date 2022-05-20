package com.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.AuthenticationProvider;
import com.security.entity.User;
import com.security.repository.UserRepository;
import com.security.service.UserService;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {

		userService.processRegister(user);
		return "register_success";
	}

	@GetMapping("/users")
	public String listOfUsers(Model model) {
		List<User> listUsers = userRepository.findAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

}
