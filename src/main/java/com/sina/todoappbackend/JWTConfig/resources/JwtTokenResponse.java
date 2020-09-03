package com.sina.todoappbackend.JWTConfig.resources;

public class JwtTokenResponse {
	
	private final String token;

	public JwtTokenResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
	

}
