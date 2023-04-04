package com.piseth.java.school.schoolManagement.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder  passwordEncoder;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET ,"/monthlyScores/*").hasAnyAuthority(Permission.MS_R.getDescript())  //.hasAnyRole("STUDENT", "TEACHER")
		.antMatchers(HttpMethod.POST ,"/monthlyScores").hasAnyAuthority(Permission.MS_W.getDescript())   //.hasAnyRole("TEACHER")
		.antMatchers(HttpMethod.PUT ,"/monthlyScores/*").hasAnyAuthority(Permission.MS_W.getDescript())  //.hasAnyRole("TEACHER")
		.anyRequest()
		.authenticated()
		.and()
	    .httpBasic();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		
		UserDetails Dara_S = User.builder()
									.username("Dara")
									.password(passwordEncoder.encode("dara123"))
									//.roles("STUDENT")
									.authorities(Role.STUDENT.getAuthority())
									.build();
		
		UserDetails Leo_T = User.builder()
									.username("Leo")
									.password(passwordEncoder.encode("leo123"))
									//.roles("TEACHER")
									.authorities(Role.TEACHER.getAuthority())
									.build();
		
		return new InMemoryUserDetailsManager(Dara_S, Leo_T);
	}
}
