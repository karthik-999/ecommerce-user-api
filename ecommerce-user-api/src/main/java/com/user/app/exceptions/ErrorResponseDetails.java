package com.user.app.exceptions;

import java.util.List;

public class ErrorResponseDetails {

	List<String> errorDetails;
	
	String message;

	public ErrorResponseDetails(String message, List<String> details) {
		this.message = message;
		this.errorDetails = details;

	}

	public List<String> getErrorDetails() {
		return errorDetails;
	}

	public String getMessage() {
		return message;
	}

	public void setErrorDetails(List<String> errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
