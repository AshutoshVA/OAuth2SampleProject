package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.AuthenticationProvider;
import com.security.entity.User;
import com.security.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepository.save(user);
		return "";
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User getUserByUsername(String name) {
		return userRepository.getUserByUsername(name);
	}

	public void createNewUserAfterOauthLoginSuccess(String email, String name, AuthenticationProvider provider) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setProvider(provider);
		userRepository.save(user);
	}

	public void updateUserAfterOauthLoginSuccess(String email, User user, String name,
			AuthenticationProvider provider) {
		user.setName(name);
		user.setEmail(email);
		user.setProvider(provider);
		userRepository.save(user);

	}

}
