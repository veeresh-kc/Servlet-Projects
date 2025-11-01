package com.demo.daos;

import java.util.List;
import java.util.Optional;

import com.demo.model.Employee;

public interface EmployeeDAO {
	List<Employee> findAll();
	Optional<Employee> findById(Integer id);
	int insert(Employee e);
	int update(Employee e);
	int delete(Integer id);
}
