package com.greatlearning.employee.service;

import java.util.List;

import com.greatlearning.employee.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(long id);

	Employee updateEmployee(Employee employee, long id);

	String deleteEmployee(long id);

	List<Employee> findByFirstName(String firstName);

	List<Employee> getAllEmployeesSorted(String order);

}
