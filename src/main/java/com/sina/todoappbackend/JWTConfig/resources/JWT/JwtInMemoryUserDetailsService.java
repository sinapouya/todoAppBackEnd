package com.sina.todoappbackend.JWTConfig.resources.JWT;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService{

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<JwtUserDetails>();
	static {
		inMemoryUserList.add(new JwtUserDetails(1l, "sina", 
				"$2a$10$5TPQplg97v740OEDx/9ZJ.E5i.pfrydvpkK9N1k/sFltGKC.Hxao6", "ROLE_USER_2"));//Zling4u@
		inMemoryUserList.add(new JwtUserDetails(2l, "user", 
				"$2a$10$l.WAkZU6utRTiJOP43IZ9Ogv3bYtVeSZoE70kg/wZbrc.y1.u9NY6", "ROLE_USER_2"));//passw0rd@123#123
		
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<JwtUserDetails> findFrist = inMemoryUserList.stream().filter(user -> user.getUsername()
				.equalsIgnoreCase(username))
				.findFirst();
		if(!findFrist.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}
		return findFrist.get();
	}
	
	
	
}
