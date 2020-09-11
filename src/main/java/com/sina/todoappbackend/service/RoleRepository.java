package com.sina.todoappbackend.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sina.todoappbackend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRolename(String roleName);
}
