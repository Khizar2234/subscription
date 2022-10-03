package com.ros.administration.exceptions;

public class SubscriptionNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubscriptionNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubscriptionNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public SubscriptionNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public SubscriptionNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SubscriptionNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
