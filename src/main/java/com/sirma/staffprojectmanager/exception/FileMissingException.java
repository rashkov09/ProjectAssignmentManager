package com.sirma.staffprojectmanager.exception;

public class FileMissingException extends RuntimeException {

	private static final String ERROR_MESSAGE = "File with path %s not found!";

	public FileMissingException(String path) {
		super(String.format(ERROR_MESSAGE, path));
	}
}
