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
import com.sina.todoappbackend.service.ToDoJpaRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {
	@Autowired
	private ToDoJpaRepository toDoService;
	
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
			toDoService.deleteById(todoId);
			if(toDoService.existsById(todoId)) {
				return ResponseEntity.noContent().build();
			}else {
				return ResponseEntity.notFound().build();
			}			
	}
	@PutMapping(value = "/users/{userName}/todos/{todoId}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String userName,
										   @PathVariable Long todoId,
										    @RequestBody Todo todo){
		Todo todoUpdated = toDoService.save(todo);
		return new ResponseEntity<Todo>(todoUpdated,HttpStatus.OK);
		
	}
	@PostMapping(value = "/users/{userName}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String userName,
										    @RequestBody Todo todo){
		Todo createdTodo = toDoService.save(todo);
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
