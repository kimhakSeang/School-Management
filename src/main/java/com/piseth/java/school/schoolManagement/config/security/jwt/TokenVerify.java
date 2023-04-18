package com.piseth.java.school.schoolManagement.config.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.piseth.java.school.schoolManagement.exception.ApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class TokenVerify extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorization = request.getHeader("Authorization").replace("Bearer Bearer ", "Bearer ");
		byte[] key ="Password123456789abcdefghijklmnopqrs".getBytes()	;
		String msg = authorization;
		if(Objects.isNull(authorization) || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);  
//			throw new ApiException(HttpStatus.BAD_REQUEST, msg);
			return;
		}
		
		String token = authorization.replace("Bearer ", "");
		
		Jws<Claims> claimJws = Jwts.parser()
									.setSigningKey(Keys.hmacShaKeyFor(key))
									.parseClaimsJws(token);
		Claims body = claimJws.getBody();
		
		String username = body.getSubject();
		List<Map<String, String>> listAuthorities = (List<Map<String, String>>)body.get("authorities");
		
        Set<SimpleGrantedAuthority> authorities = listAuthorities
        													.stream()
        													.map(m->new SimpleGrantedAuthority(m.get("authority")))
        													.collect(Collectors.toSet());
		
        Authentication  authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);  
	}
}
