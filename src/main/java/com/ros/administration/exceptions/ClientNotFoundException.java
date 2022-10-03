package com.ros.administration.exceptions;

public class ClientNotFoundException extends Exception {

    public ClientNotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ClientNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);

    }

    public ClientNotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ClientNotFoundException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
