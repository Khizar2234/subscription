package com.ros.administration.exceptions;

public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
