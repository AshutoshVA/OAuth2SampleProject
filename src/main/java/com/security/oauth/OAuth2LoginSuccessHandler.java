package com.security.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.security.AuthenticationProvider;
import com.security.entity.User;
import com.security.repository.UserRepository;
import com.security.service.UserService;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
		String email = oauth2User.getEmail();
		String name = oauth2User.getFullName();
		User user = userService.getUserByEmail(email);
		if (user == null) {
			userService.createNewUserAfterOauthLoginSuccess(email, name, AuthenticationProvider.GOOGLE);

			response.sendRedirect("/users");

		} else {
			userService.updateUserAfterOauthLoginSuccess(name, user, email, AuthenticationProvider.GOOGLE);
		}
		System.out.println(email);
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
