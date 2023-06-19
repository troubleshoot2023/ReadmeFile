package com.profile.service;


import java.util.List;

import com.profile.dto.EmployeeDto;
import com.profile.response.Response;

public interface EmployeeService {

	 Response saveEmployeeInfo(EmployeeDto dto);

	 List<EmployeeDto> fetchAllEmployee();
	
	 Response findByEmail(String email);
	
	 Response findById(Long id);
	
	 Response updateByEmail(String email,EmployeeDto dto);
	
	 String deleteByEmail(String email);
}
