package com.sina.todoappbackend.JWTConfig.resources;

public class AuthenticationException extends RuntimeException{
	public AuthenticationException(String message,Throwable cause) {
		super(message,cause);
	}
}
