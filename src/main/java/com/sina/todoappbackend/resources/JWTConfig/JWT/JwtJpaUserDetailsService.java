package com.sina.todoappbackend.resources.JWTConfig.JWT;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sina.todoappbackend.entity.User;
import com.sina.todoappbackend.service.UserJpaRepository;

@Service
public class JwtJpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserJpaRepository userJpaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<User> userOptional = userJpaRepository.findByUsername(username);
				if(!userOptional.isPresent()) {
					throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
				}
				User user = userOptional.get();
				return new JwtUserDetails(
						user.getId(), user.getUsername(), user.getPassword(), user.getRoles());
	}
	
}
