package com.ros.administration.exceptions;

public class SubscriptionAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubscriptionAlreadyExistsException() {
		super();
	}

	public SubscriptionAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public SubscriptionAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public SubscriptionAlreadyExistsException(String message) {
		super(message);
	}

	public SubscriptionAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
