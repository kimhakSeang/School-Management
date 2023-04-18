package com.piseth.java.school.schoolManagement.config.security.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UerLoginRequestFilter extends UsernamePasswordAuthenticationFilter{
	
	@SuppressWarnings("unused")
	private final  AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
		try {
			UserLoginRequest readValue = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
			
			Authentication authenticationToken = new UsernamePasswordAuthenticationToken(readValue.getUsername(), readValue.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authenticationToken);
			
			return authenticate;
			 
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String key = "Password123456789abcdefghijklmnopqrs"	;
		
		String token = Jwts.builder()
			.signWith(Keys.hmacShaKeyFor(key.getBytes()))
			.setSubject(authResult.getName())			
			.claim("authorities", authResult.getAuthorities())
			.setIssuedAt(new Date())
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
			.compact();
			
		response.addHeader("Authorization",token);
		
	}
}
