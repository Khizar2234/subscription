package com.ros.administration.exceptions;

public class CountryAlreadyExistsException extends Exception {

	public CountryAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CountryAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CountryAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CountryAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CountryAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
