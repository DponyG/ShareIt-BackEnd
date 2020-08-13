package com.shareit.exception;

public class ServiceException extends Exception {
	
	private int statusCode;
	public ServiceException(String message, int statusCode) {
		super(message);
		this.setStatusCode(statusCode);
		
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
