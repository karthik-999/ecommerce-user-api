package com.user.app.exceptions;

import java.util.Date;

public class ErrorMessage {

	private Date currentDate;
	
	private String errorMessageDescription;
	
	
	public ErrorMessage(Date currentDate, String errorMessageDescription) {
		super();
		this.currentDate = currentDate;
		this.errorMessageDescription = errorMessageDescription;
	}


	public Date getCurrentDate() {
		return currentDate;
	}


	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}


	public String getErrorMessageDescription() {
		return errorMessageDescription;
	}


	public void setErrorMessageDescription(String errorMessageDescription) {
		this.errorMessageDescription = errorMessageDescription;
	}


	public ErrorMessage() {

		
		
	}

}
