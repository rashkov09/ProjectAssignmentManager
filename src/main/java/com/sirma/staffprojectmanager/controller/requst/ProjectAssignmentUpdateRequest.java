package com.sirma.staffprojectmanager.controller.requst;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class ProjectAssignmentUpdateRequest {
	@NotNull
	private Long employeeId;
	@NotNull
	private Long projectId;
	@NotNull
	@Pattern(regexp = "^([0-9]{4})/(0[1-9]|1[0-2])/([0-2][0-9]|3[0-1])|([0-2][0-9]|3[0-1])/" +
	                  "(0[1-9]|1[0-2])/([0-9]{4})|(0[1-9]|1[0-2])/([0-2][0-9]|3[0-1])/(\\d{4})|([0-9]{4})-" +
	                  "(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1])|([0-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-([0-9]{4})|" +
	                  "(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1])-(\\d{4})$", message = "Invalid date format!")
	private LocalDate dateFrom;
	private LocalDate dateTo;

	public ProjectAssignmentUpdateRequest() {
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

}
