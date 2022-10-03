package com.ros.administration.exceptions;

public class RestaurantAlreadyExistsException extends Exception {

	public RestaurantAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestaurantAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public RestaurantAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public RestaurantAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RestaurantAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
