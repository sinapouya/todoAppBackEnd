package com.sina.todoappbackend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		for(int i=0;i<10;i++) {
			String encodedString = enc.encode("passw0rd@123#123");
			System.out.println(encodedString);	
		}
		

	}

}
