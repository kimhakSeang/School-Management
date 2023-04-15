package com.piseth.java.school.schoolManagement.config.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Service
public class FakseAuthUser implements UserDetailServices  {
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getUserDetails().stream().filter(s -> s.getUsername().equals(username)).findFirst()
				.orElseThrow(()->new UsernameNotFoundException("%s is not exist!".formatted(username)));
	}

	private Set<UserDetails> getUserDetails() {
		UserDetails dara_S = new User("dara", encoder.encode("dara123"), Set.of(new SimpleGrantedAuthority(PermissionEnum.MS_R.getDescript())));
		UserDetails thida_T = new User("thida", encoder.encode("thida123"), Set.of(new SimpleGrantedAuthority(PermissionEnum.MS_W.getDescript()), new SimpleGrantedAuthority(PermissionEnum.MS_R.getDescript())));
		return Set.of(dara_S, thida_T);
	}

}
