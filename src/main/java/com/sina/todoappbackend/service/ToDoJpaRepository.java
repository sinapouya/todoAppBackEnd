package com.sina.todoappbackend.service;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sina.todoappbackend.entity.Todo;

@Repository
public interface ToDoJpaRepository extends JpaRepository<Todo,Long>{

}
