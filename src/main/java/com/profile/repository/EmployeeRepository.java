package com.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.profile.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee,Long> {

	boolean existsByEmail(String email);
	public Employee findByEmail(String email);
}
