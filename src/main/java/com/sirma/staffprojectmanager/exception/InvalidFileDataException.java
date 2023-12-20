package com.sirma.staffprojectmanager.exception;

public class InvalidFileDataException extends RuntimeException{
	private static final String NO_DATA_AVAILABLE_MESSAGE = "Invalid data in file! Error: %s";

	public InvalidFileDataException(String message) {
		super(String.format(NO_DATA_AVAILABLE_MESSAGE, message));
	}
}
