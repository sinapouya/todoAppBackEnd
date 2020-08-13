package com.sina.todoappbackend.entity;

public class Message {
	private String message;

	public Message(String message) {
		super();
		this.message = message;
	}

	public Message() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
