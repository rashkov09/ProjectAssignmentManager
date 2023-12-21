package com.sirma.staffprojectmanager.handler;

import com.sirma.staffprojectmanager.exception.FileMissingException;
import com.sirma.staffprojectmanager.exception.InvalidFileDataException;
import com.sirma.staffprojectmanager.exception.ProjectAssignmentNotFoundException;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProjectAssignmentNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFountException(ProjectAssignmentNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidFileDataException.class)
	public ResponseEntity<String> handleEmployeeNotFountException(InvalidFileDataException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FileMissingException.class)
	public ResponseEntity<String> handleEmployeeNotFountException(FileMissingException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

}
