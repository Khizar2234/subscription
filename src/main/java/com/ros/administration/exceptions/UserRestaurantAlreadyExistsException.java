package com.ros.administration.exceptions;

public class UserRestaurantAlreadyExistsException extends Exception {

	public UserRestaurantAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRestaurantAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UserRestaurantAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserRestaurantAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserRestaurantAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
