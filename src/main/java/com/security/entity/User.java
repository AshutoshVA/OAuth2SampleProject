package com.security.entity;

import javax.persistence.*;

import com.security.AuthenticationProvider;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = true, length = 64)
	private String password;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "auth_provider")
	private AuthenticationProvider provider;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AuthenticationProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthenticationProvider provider) {
		this.provider = provider;
	}

}
