package com.sina.todoappbackend.JWTConfig.resources;

import java.io.Serializable;

public class JwtTokenRequst implements Serializable{
	
	private String username;
	private String password;
	public JwtTokenRequst() {
		super();
	}
	public JwtTokenRequst(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
