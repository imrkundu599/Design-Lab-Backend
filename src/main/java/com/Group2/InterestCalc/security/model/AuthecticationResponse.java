package com.Group2.InterestCalc.security.model;


import org.springframework.stereotype.Component;

import com.Group2.InterestCalc.Resources.User;

@Component
public class AuthecticationResponse {

	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String jwt;

	public AuthecticationResponse() {
	}

	public AuthecticationResponse(String jwt,User user) {
		this.jwt = jwt;
		this.email=user.getEmail();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
		this.userId=user.getUserId();
	}

	public String getJwt() {
		return jwt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
