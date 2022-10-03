package com.ros.administration.exceptions;

public class CurrencyAlreadyExistsException extends Exception {

	public CurrencyAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrencyAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CurrencyAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CurrencyAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CurrencyAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
