package com.profile.exception;

public class UserNotFound extends RuntimeException {
	
	private String message;

	public UserNotFound(String string) {
		this.message = string;
	}
	

	public UserNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	private static final long serialVersionUID = 1L;

}
