package com.sina.todoappbackend.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sina.todoappbackend.entity.Todo;
import com.sina.todoappbackend.service.HardCodedToDoService;

public class todoControllerTest {
	
	@InjectMocks
	TodoController todoController;
	@Mock
	HardCodedToDoService toDoService;

	List<Todo> listOfTodos;
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		listOfTodos=new ArrayList<Todo>();int idCounter=1;
		listOfTodos.add(new Todo(++idCounter,"sina","dentist session",new Date(),false));
		listOfTodos.add(new Todo(++idCounter,"sina","interview session",new Date(),false));
		listOfTodos.add(new Todo(++idCounter,"ali","study streams",new Date(),true));
		listOfTodos.add(new Todo(++idCounter,"ali","study spring boot",new Date(),false));

	}	
	@Test
	final void getAllTodoesTest(){
		
		when(toDoService.findAll()).thenReturn(listOfTodos);
		assertEquals(2, todoController.getAllTodoes("ali").size());
	}

}
