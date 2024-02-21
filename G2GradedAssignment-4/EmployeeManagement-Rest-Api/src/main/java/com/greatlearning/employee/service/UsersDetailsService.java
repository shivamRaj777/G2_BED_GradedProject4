package com.greatlearning.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.greatlearning.employee.model.User;
import com.greatlearning.employee.repository.UserRepository;
import com.greatlearning.employee.security.MyUserDetails;

@Component
public class UsersDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUsername(username);

		return user.map(MyUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

	}

}
