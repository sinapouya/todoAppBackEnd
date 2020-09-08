package com.sina.todoappbackend.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sina.todoappbackend.resources.JWTConfig.JWT.JwtUserDetails;

public interface UserDetailsJpaRepository extends JpaRepository<JwtUserDetails,Long>{

}
