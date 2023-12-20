package com.sirma.staffprojectmanager.exception;

public class ProjectAssignmentNotFoundException extends RuntimeException{

	private final static String NOT_FOUND_MESSAGE = "Project assignment with id %s not found!";
	public ProjectAssignmentNotFoundException(String id) {
		super(String.format(NOT_FOUND_MESSAGE,id));
	}
}
