package com.sina.todoappbackend.resources.JWTConfig;

public class AuthenticationException extends RuntimeException{
	public AuthenticationException(String message,Throwable cause) {
		super(message,cause);
	}
}
