package com.anurag.springbootapp.exception;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
