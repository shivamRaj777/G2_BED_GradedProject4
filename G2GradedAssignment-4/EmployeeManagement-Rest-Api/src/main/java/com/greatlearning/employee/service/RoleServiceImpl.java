package com.greatlearning.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.employee.model.Role;
import com.greatlearning.employee.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository repository;

	@Override
	public Role createRole(Role role) {
		return repository.save(role);
	}

}
