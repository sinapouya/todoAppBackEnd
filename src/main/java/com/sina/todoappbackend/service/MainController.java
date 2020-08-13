package com.sina.todoappbackend.service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sina.todoappbackend.entity.Message;

@RestController
@RequestMapping(value = "todo/v1.0.1")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {
	@GetMapping(value = "sayHello")
	public Message sayHello() {
		return new Message("hello heroku world!");
	}
	
}
