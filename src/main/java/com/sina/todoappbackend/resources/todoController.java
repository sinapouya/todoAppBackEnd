package com.sina.todoappbackend.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sina.todoappbackend.entity.Todo;
import com.sina.todoappbackend.service.HardCodedToDoService;

@RestController
public class todoController {
	@Autowired
	private HardCodedToDoService toDoService;
	
	@GetMapping(value = "/users/{userName}/todos")
	public List<Todo> getAllTodoes(@PathVariable String userName){
		return toDoService.findAll().stream().filter(todo->
											todo.getUserName().equalsIgnoreCase(userName))
											.collect(Collectors.toList());
											
	}
}
