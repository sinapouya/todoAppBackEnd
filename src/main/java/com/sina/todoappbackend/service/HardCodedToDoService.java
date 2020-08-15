package com.sina.todoappbackend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sina.todoappbackend.entity.Todo;

@Service
public class HardCodedToDoService {
	private static List<Todo> listOfTodos=new ArrayList<Todo>();
	private static Long idCounter=1l;
	static {
		listOfTodos.add(new Todo(++idCounter,"sina","dentist session",new Date(),false));
		listOfTodos.add(new Todo(++idCounter,"sina","interview session",new Date(),false));
		listOfTodos.add(new Todo(++idCounter,"ali","study streams",new Date(),true));
		listOfTodos.add(new Todo(++idCounter,"ali","study spring boot",new Date(),false));

	}
	public List<Todo> findAll(){
		return listOfTodos;
	}
	public Optional<Todo> deleteById(Long id) {
		Optional<Todo> todo = findById(id);
		todo.ifPresent(item->listOfTodos.remove(item));
		return todo;
	}
	public Optional<Todo> findById(Long id) {
		Optional<Todo> todo = listOfTodos.stream()
			.filter(item->item.getId().equals(id))
			.findAny();
		return todo;
	}
	
}
