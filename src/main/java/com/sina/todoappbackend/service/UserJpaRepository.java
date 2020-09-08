package com.sina.todoappbackend.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sina.todoappbackend.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
}
