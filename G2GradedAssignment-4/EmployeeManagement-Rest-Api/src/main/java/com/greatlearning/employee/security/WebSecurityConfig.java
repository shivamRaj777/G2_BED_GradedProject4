package com.greatlearning.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.greatlearning.employee.service.UsersDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable().authorizeRequests().requestMatchers("/api/employees/list")
				.hasAnyAuthority("ADMIN", "USER", "HR").and().authorizeRequests()
				.requestMatchers("/api/employees/addRole", "/api/employees/addUser", "/api/employees/new",
						"/api/employees/view/{id}", "/api/employees/update/{id}", "/api/employees/delete/{id}",
						"/api/employees/search", "/api/employees/sort")
				.hasAuthority("ADMIN").anyRequest().authenticated().and().formLogin().and().httpBasic().and()
				.exceptionHandling().and().build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UsersDetailsService();
	}
}
