package com.profile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.profile.dto.EmployeeDto;
import com.profile.exception.UserExist;
import com.profile.exception.UserNotFound;
import com.profile.model.Employee;
import com.profile.repository.EmployeeRepository;
import com.profile.response.Response;
import com.profile.sequence.SequenceGeneratorService;

@SuppressWarnings("unused")
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private SequenceGeneratorService sequence;
	
	@Value("${server.port}")
	String portNumber;
	
	@Value("${spring.application.name}")
	String AppName;
	

	@Override
	public Response saveEmployeeInfo(EmployeeDto dto) {

		Employee e = mapToEntity(dto);
		System.out.println(e.getEmail());
		try {
			if (repo.existsByEmail(e.getEmail())) {
				throw new UserExist("Employee Exist in this Email Id "+e.getEmail()+ " try new One");
			}
		} catch (Exception e2) {
		}

		List<Employee> list = repo.findAll();

		ArrayList<Employee> list1 = new ArrayList<>();

		int count = 0;

		for (Employee employee : list) {
			count++;
		}

		if (count == 0) {
			e.setId(count + 1);
		} else {
			e.setId(count + 1);
		}
		
//		long generateSequence = sequence.generateSequence(Employee.SEQUENCE_NAME);
//		e.setId(generateSequence);
		
		Employee employee = repo.save(e);
		EmployeeDto dt = mapToDto(employee);
		Response response = new Response();
		response.setDto(dto);
		response.setAppLicationName(AppName);
		response.setPortNumber(portNumber);
		return response;
	}

	@Override
	public List<EmployeeDto> fetchAllEmployee() {
		List<Employee> list = repo.findAll();
		List<EmployeeDto> list2 = list.stream().map(e->mapToDto(e)).collect(Collectors.toList());
		return list2;
	}
	
	@Override
	public Response findByEmail(String email) {
		Employee employee = repo.findByEmail(email);
		if (employee==null) {
			throw new UserNotFound("Employee not exist in this email "+email);
		}
		EmployeeDto dto = mapToDto(employee);
		Response r = new Response();
		r.setAppLicationName(AppName);
		r.setDto(dto);
		r.setPortNumber(portNumber);
		return r;
	}
	

	@Override
	public Response findById(Long id) {
		Employee employee = repo.findById(id).get();
		EmployeeDto dto = mapToDto(employee);
		Response r = new Response();
		r.setAppLicationName(AppName);
		r.setDto(dto);
		r.setPortNumber(portNumber);
		return r;
	}
	
	@Override
	public Response updateByEmail(String email,EmployeeDto dto) {
		Employee employee = repo.findByEmail(email);
		Employee e = mapToEntity(dto);
		employee.setEmail(e.getEmail());
		employee.setFirstName(e.getFirstName());
		employee.setLastName(e.getLastName());
		employee.setGender(e.getGender());
		employee.setMobile(e.getMobile());
		employee.setPassword(e.getPassword());
		Employee employee2 = repo.save(employee);
		EmployeeDto dto2 = mapToDto(employee2);
		
		Response r = new Response();
		r.setAppLicationName(AppName);
		r.setDto(dto2);
		r.setPortNumber(portNumber);
		
		return r;
	}

	@Override
	public String deleteByEmail(String email) {
		Employee employee = repo.findByEmail(email);
		repo.deleteById(employee.getId());
		return "Successfully Employee deleted "+email;
	}

	public Employee mapToEntity(EmployeeDto dto) {
		Employee employee = new Employee();
		employee.setEmail(dto.getEmail());
		employee.setFirstName(dto.getFirstName());
		employee.setGender(dto.getGender());
		employee.setLastName(dto.getLastName());
		employee.setMobile(dto.getMobile());
		employee.setPassword(dto.getPassword());
		return employee;
	}
	

	public EmployeeDto mapToDto(Employee dto) {
		EmployeeDto employee = new EmployeeDto();
		employee.setEmail(dto.getEmail());
		employee.setFirstName(dto.getFirstName());
		employee.setGender(dto.getGender());
		employee.setLastName(dto.getLastName());
		employee.setMobile(dto.getMobile());
		employee.setPassword(dto.getPassword());
		return employee;
	}

}
