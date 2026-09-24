package com.signup.exception;

public class PhoneNumberAlreadyExistsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhoneNumberAlreadyExistsException(String message)
	{
		super(message);
	}

}
