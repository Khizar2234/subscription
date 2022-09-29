package com.ros.administration.exceptions;

public class ConfigurationNotFoundException extends Exception {

	public ConfigurationNotFoundException() {
		super();
	}

	public ConfigurationNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConfigurationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigurationNotFoundException(String message) {
		super(message);
	}

	public ConfigurationNotFoundException(Throwable cause) {
		super(cause);
	}

}
