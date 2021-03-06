package com.sina.todoappbackend.resources.JWTConfig;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sina.todoappbackend.resources.JWTConfig.JWT.JWTWebSecurityConfig;
import com.sina.todoappbackend.resources.JWTConfig.JWT.JwtInMemoryUserDetailsService;
import com.sina.todoappbackend.resources.JWTConfig.JWT.JwtJpaUserDetailsService;
import com.sina.todoappbackend.resources.JWTConfig.JWT.JwtTokenUtil;
import com.sina.todoappbackend.resources.JWTConfig.JWT.JwtUserDetails;
@RestController
@CrossOrigin(value = "*")
public class JwtAuthenticationRestController {
	
	@Value(value = "${jwt.http.request.header}")
	private String tokenHeader;
	
	@Autowired
    private JWTWebSecurityConfig jwtWebSecurityConfig;
	
	@Autowired
	private JwtJpaUserDetailsService jwtJpaUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil; 
	
	@PostMapping(value = "${jwt.get.token.uri}")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequst jwtTokenRequst)
		throws AuthenticationException{
		authenticate(jwtTokenRequst.getUsername(),jwtTokenRequst.getPassword());
		
		final UserDetails userDetails = jwtJpaUserDetailsService.
				loadUserByUsername(jwtTokenRequst.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtTokenResponse(token));
	}
	@RequestMapping(value = "${jwt.refresh.token.uri}",method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthToken(HttpServletRequest request){
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		String username = jwtTokenUtil.getUserNameFromToken(token);
	    JwtUserDetails user = (JwtUserDetails) jwtJpaUserDetailsService.loadUserByUsername(username);
    	if (jwtTokenUtil.canTokenBeRefreshed(token)) {
	        String refreshedToken = jwtTokenUtil.refreshToken(token);
	        return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
	      } else {
	        return ResponseEntity.badRequest().body(null);
	      }
	    
	}
	@ExceptionHandler({ AuthenticationException.class })
	  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	  }
	private void authenticate(String username,String password) throws AuthenticationException{
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		
		try {
			AuthenticationManager myAuthenticationManager=jwtWebSecurityConfig.authenticationManagerBean();
			myAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}catch (DisabledException e) {
			throw new AuthenticationException("USER_DISABLED",e);
		}catch (BadCredentialsException e) {
			throw new AuthenticationException("INVALID_CREDENTIALS",e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
