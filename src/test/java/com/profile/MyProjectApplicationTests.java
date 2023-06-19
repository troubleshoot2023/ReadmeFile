package com.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.profile.dto.EmployeeDto;
import com.profile.model.Employee;
import com.profile.repository.EmployeeRepository;
import com.profile.response.Response;
import com.profile.service.EmployeeService;

@SpringBootTest
@RunWith(SpringRunner.class)
class MyProjectApplicationTests {

	
	
	@Autowired
	private EmployeeService service;
	
	@MockBean
	private EmployeeRepository repo;
	
	@Value("${server.port}")
	String portNumber;
	
	@Value("${spring.application.name}")
	String AppName;
	
	
	@Test
	public void fetchAllEmployeeTest() {   //Properly Working
		Employee e = new Employee();
		Employee e1 = new Employee();
		e.setEmail("abc@gmail.com");
		e.setFirstName("Rahul");
		e.setGender("male");
		e.setLastName("Bhardwaj");
		e.setMobile(973245605);
		e.setPassword("abcd");
		e1.setEmail("xyz@gmail.com");
		e1.setFirstName("Amit");
		e1.setGender("male");
		e1.setLastName("Bhardwaj");
		e1.setMobile(873245605);
		e1.setPassword("xyz");
		ArrayList<Employee> list = new ArrayList<>();
		list.add(e);
		list.add(e1);
		
		 when(repo.findAll()).thenReturn(list);
		 assertEquals(2,service.fetchAllEmployee().size());
	}
	
	@Test
	public void saveEmployee() {      //Not Working
		Employee e = new Employee();
		
		e.setId(1);
		e.setEmail("rahul@gmail.com");
		e.setFirstName("Rahul");
		e.setGender("male");
		e.setLastName("Bhardwaj");
		e.setMobile(973245605);
		e.setPassword("abcd");
		
		when(repo.save(e)).thenReturn(e);
		
		EmployeeDto dto = mapToDto(e);
		Response r= new Response();
		r.setDto(dto);
		r.setAppLicationName(AppName);
		r.setPortNumber(portNumber);
		
		assertEquals(dto, service.saveEmployeeInfo(dto));
		
	
	}
	
	@Test
	public void fetchOneEmployeeByEmail() {      //Not Working
		Employee e = new Employee();
		e.setId(1);
		e.setEmail("abc@gmail.com");
		e.setFirstName("Rahul");
		e.setGender("male");
		e.setLastName("Bhardwaj");
		e.setMobile(973245605);
		e.setPassword("abcd");
		
		String email =  "abc@gmail.com";
		
		when(repo.findByEmail(email)).thenReturn(e);
		
		EmployeeDto dto = mapToDto(e);
		
		Response r= new Response();
		
		r.setDto(dto);
		r.setAppLicationName(AppName);
		r.setPortNumber(portNumber);
		
		assertEquals(r, service.findByEmail(email));
	}
	
	@Test
	public void deleteByEmail() {     //Not Working
		Employee e = new Employee();
		e.setId(1);
		e.setEmail("abc@gmail.com");
		e.setFirstName("Rahul");
		e.setGender("male");
		e.setLastName("Bhardwaj");
		e.setMobile(973245605);
		e.setPassword("abcd");
		
		String email =  "abc@gmail.com";
		
		service.deleteByEmail(email);
		
		verify(repo,times(1)).delete(e);
		
	}
	
	
	public Employee mapToEntity(EmployeeDto dto) {
		Employee employee = new Employee();
		employee.setId(dto.getId());
		employee.setEmail(dto.getEmail());
		employee.setFirstName(dto.getFirstName());
		employee.setGender(dto.getGender());
		employee.setLastName(dto.getLastName());
		employee.setMobile(dto.getMobile());
		employee.setPassword(dto.getPassword());
		return employee;
	}
	

	public EmployeeDto mapToDto(Employee e) {
		EmployeeDto dto = new EmployeeDto();
		dto.setId(e.getId());
		dto.setEmail(e.getEmail());
		dto.setFirstName(e.getFirstName());
		dto.setGender(e.getGender());
		dto.setLastName(e.getLastName());
		dto.setMobile(e.getMobile());
		dto.setPassword(e.getPassword());
		return dto;
	}

}
