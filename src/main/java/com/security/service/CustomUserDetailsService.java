package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.AuthenticationProvider;
import com.security.CustomUserDetails;
import com.security.entity.User;
import com.security.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}

	public void processOAuthPostLogin(String name) {
		User existUser = userRepository.getUserByUsername(name);

		if (existUser == null) {
			User newUser = new User();
			newUser.setName(name);
			newUser.setProvider(AuthenticationProvider.GOOGLE);

			userRepository.save(newUser);
		}

	}

}
