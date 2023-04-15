package com.piseth.java.school.schoolManagement.config.security;

import static com.piseth.java.school.schoolManagement.config.security.PermissionEnum.MS_R;
import static com.piseth.java.school.schoolManagement.config.security.PermissionEnum.MS_W;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter{

	
	
	@Autowired
	private PasswordEncoder  passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET ,"/monthlyScores/*").hasAnyAuthority( MS_R.toString())
		.antMatchers(HttpMethod.POST ,"/monthlyScores/*").hasAnyAuthority(MS_R.toString(), MS_W.toString())
		.antMatchers(HttpMethod.PUT ,"/monthlyScores/*").hasAnyAuthority( MS_R.toString(), MS_W.toString())
		.anyRequest()
		.authenticated()
		.and()
	    .httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}
	
	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() {
	 * 
	 * UserDetails Dara_S = User.builder() .username("Dara")
	 * .password(passwordEncoder.encode("dara123")) //.roles("STUDENT")
	 * .authorities(UserRoleEnum.STUDENT.getAuthority()) .build();
	 * 
	 * UserDetails Leo_T = User.builder() .username("Leo")
	 * .password(passwordEncoder.encode("leo123")) //.roles("TEACHER")
	 * .authorities(UserRoleEnum.TEACHER.getAuthority()) .build();
	 * 
	 * return new InMemoryUserDetailsManager(Dara_S, Leo_T); }
	 */
	
	
}
