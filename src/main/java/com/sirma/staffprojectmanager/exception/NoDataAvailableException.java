package com.sirma.staffprojectmanager.exception;

public class NoDataAvailableException extends RuntimeException{
	private static final String NO_DATA_AVAILABLE_MESSAGE = "No data available!";

	public NoDataAvailableException() {
		super(NO_DATA_AVAILABLE_MESSAGE);
	}
}
