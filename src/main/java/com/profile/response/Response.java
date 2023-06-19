package com.profile.response;


import com.profile.dto.EmployeeDto;

public class Response {

	private EmployeeDto dto;
	private String portNumber;
	private String AppLicationName;
	
	public Response(EmployeeDto dto, String portNumber, String appLicationName) {
		super();
		this.dto = dto;
		this.portNumber = portNumber;
		AppLicationName = appLicationName;
	}
	public String getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	public String getAppLicationName() {
		return AppLicationName;
	}
	public void setAppLicationName(String appLicationName) {
		AppLicationName = appLicationName;
	}
	public EmployeeDto getDto() {
		return dto;
	}
	public void setDto(EmployeeDto dto) {
		this.dto = dto;
	}
	
	public Response(EmployeeDto dto) {
		super();
		this.dto = dto;
	}
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
