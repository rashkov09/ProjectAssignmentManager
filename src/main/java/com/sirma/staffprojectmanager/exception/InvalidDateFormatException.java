package com.sirma.staffprojectmanager.exception;

public class InvalidDateFormatException extends RuntimeException{
	private final static String MESSAGE="Invalid date format %s";

	public InvalidDateFormatException(String message) {
		super(String.format(MESSAGE,message));
	}
}
