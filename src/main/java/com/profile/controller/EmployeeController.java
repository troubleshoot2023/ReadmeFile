package com.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.profile.dto.EmployeeDto;
import com.profile.response.Response;
import com.profile.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@Value("${server.port}")
	String portNumber;
	
	@Value("${spring.application.name}")
	String AppName;
	

	@PostMapping
	public ResponseEntity<Object> saveData(@RequestBody EmployeeDto dto) {
		EmployeeDto dt = service.saveEmployeeInfo(dto);
		Response response = new Response();
		response.setDto(dt);
		response.setAppLicationName(AppName);
		response.setPortNumber(portNumber);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getData() {
		 List<EmployeeDto> list = service.fetchAllEmployee();
		return new ResponseEntity<>(list,HttpStatus.FOUND);
	}
	
	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<Response> getByEmail(@PathVariable("email")String email) {
		 Response response = service.findByEmail(email);
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Response> getByEmail(@PathVariable("id")long id) {
		Response response = service.findById(id);
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PutMapping
	public ResponseEntity<Response> updateByEmail(@RequestParam("email")String email,@RequestBody EmployeeDto dto) {
		 Response response = service.updateByEmail(email, dto);
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<String> deleteByEmail(@PathVariable("email")String email) {
		 String message = service.deleteByEmail(email);
		return new ResponseEntity<>(message,HttpStatus.FOUND);
	}
	
}
