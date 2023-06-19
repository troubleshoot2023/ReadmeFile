package com.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import com.profile.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.profile.dto.EmployeeDto;
import com.profile.model.Employee;
import com.profile.repository.EmployeeRepository;
import com.profile.service.EmployeeService;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class MyProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmployeeService service;

	@MockBean
	private EmployeeRepository repo;


	@Test
	public void saveEmployee() {
		EmployeeDto e = new EmployeeDto();
		//System.out.println(e.getMobile());
		e.setEmail("rahul@gmail.com");
		e.setFirstName("Rahul");
		e.setGender("male");
		e.setLastName("Bhardwaj");
		e.setMobile(973245605);
		e.setPassword("abcd");
		Employee entity = mapToEntity(e);
		when(repo.save(entity)).thenReturn(entity);
		System.out.println(service.saveEmployeeInfo(e));
		assertEquals(e, service.saveEmployeeInfo(e));
	}

	@Test
	public void fetchAllEmployeeTest() {
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
		assertEquals(2, service.fetchAllEmployee().size());
	}


	@Test
	public void fetchOneEmployeeByEmail() {
		EmployeeDto e = new EmployeeDto();
		e.setEmail("abc@gmail.com");
		e.setFirstName("Rahul");
		e.setGender("male");
		e.setLastName("Bhardwaj");
		e.setMobile(973245605);
		e.setPassword("abcd");
		String email = "abc@gmail.com";
		when(repo.findByEmail(email)).thenReturn(mapToEntity(e));
		Response r = new Response();
		r.setAppLicationName("Employee-Service");
		r.setDto(e);
		r.setPortNumber("8080");
		assertEquals(r.getDto().getFirstName(), service.findByEmail(email).getDto().getFirstName());
	}

	@Test
	public void deleteByEmail() {
		Employee e = new Employee();
		e.setId(1);
		e.setEmail("abc@gmail.com");
		e.setFirstName("Rahul");
		e.setGender("male");
		e.setLastName("Bhardwaj");
		e.setMobile(973245605);
		e.setPassword("abcd");
		String email = "abc@gmail.com";
		String output = "Successfully Employee deleted " + email;
		when(repo.findByEmail(email)).thenReturn(e);
		doNothing().when(repo).deleteById(e.getId());
		// assertEquals(output, service.deleteByEmail(email));
		// verify(repo,times(1)).delete(e);
		repo.deleteById(e.getId());
		verify(repo).deleteById(e.getId());
	}

	@Test
	public void updateOneEmployee() {
		Employee e = new Employee();
		e.setId(1);
		e.setEmail("abc@gmail.com");
		e.setFirstName("Rahul");
		e.setGender("male");
		e.setLastName("Bhardwaj");
		e.setMobile(973245605);
		e.setPassword("abcd");
		String email = "abc@gmail.com";
		when(repo.findByEmail(email)).thenReturn(e);
		EmployeeDto employeeDto = new EmployeeDto();
		//System.out.println(e.getMobile());
		e.setEmail("rahul@gmail.com");
		e.setFirstName("Rahul");
		e.setGender("male");
		e.setLastName("Bhardwaj");
		e.setMobile(973245605);
		e.setPassword("abcd");
		Response r = new Response();
		r.setAppLicationName("Employee-Service");
		r.setDto(employeeDto);
		r.setPortNumber("8080");
		assertEquals(r.getDto().getFirstName(), service.updateByEmail(email, employeeDto).getDto().getFirstName());


	}


	public Employee mapToEntity(EmployeeDto dto) {
		Employee employee = new Employee();
		employee.setFirstName(dto.getFirstName());
		employee.setGender(dto.getGender());
		employee.setEmail(dto.getEmail());
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
