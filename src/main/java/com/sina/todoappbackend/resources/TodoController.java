package com.sina.todoappbackend.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sina.todoappbackend.entity.Todo;
import com.sina.todoappbackend.service.HardCodedToDoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {
	@Autowired
	private HardCodedToDoService toDoService;
	
	@GetMapping(value = "/users/{userName}/todos")
	public List<Todo> getAllTodoes(@PathVariable String userName){
		return toDoService.findAll().stream().filter(todo->
											todo.getUserName().equalsIgnoreCase(userName))
											.collect(Collectors.toList());
											
	}
	@GetMapping(value = "/users/{userName}/todos/{todoId}")
	public ResponseEntity<Todo> getTodo(@PathVariable Long todoId){
		 Optional<Todo> optionalTodo = toDoService.findById(todoId);
		 if(optionalTodo.isPresent()) {
			 return new ResponseEntity<Todo>(optionalTodo.get(),HttpStatus.OK);
		 }else {
			 return ResponseEntity.notFound().build();
		 }
											
	}
	@DeleteMapping(value = "/users/{userName}/todos/{todoId}")
	public ResponseEntity<Void> deleteById(@PathVariable String userName,
											@PathVariable Long todoId){
			Optional<Todo> todo = toDoService.deleteById(todoId);
			if(todo.isPresent()) {
				return ResponseEntity.noContent().build();
			}else {
				return ResponseEntity.notFound().build();
			}			
	}
	@PutMapping(value = "/users/{userName}/todos/{todoId}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String userName,
										   @PathVariable Long todoId,
										    @RequestBody Todo todo){
		Optional<Todo> optionalTodo = toDoService.save(todo);
		if(optionalTodo.isPresent()) {
			return new ResponseEntity<Todo>(optionalTodo.get(),HttpStatus.CREATED);
			
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	@PostMapping(value = "/users/{userName}/todos/")
	public ResponseEntity<Void> updateTodo(@PathVariable String userName,
										    @RequestBody Todo todo){
		Optional<Todo> optionalCreatedTodo = toDoService.save(todo);
		if(optionalCreatedTodo.isPresent()) {
			
			URI uri =ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(optionalCreatedTodo.get().getId()).toUri();
			return ResponseEntity.created(uri).build();
			
		}else {
			return ResponseEntity.noContent().build();
		}
	}
}
