package com.Group2.InterestCalc.security.model;

import org.springframework.stereotype.Component;

@Component
public class AuthecticationResponse {

	private String jwt;

	public AuthecticationResponse() {
		super();
	}

	public AuthecticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	
}
