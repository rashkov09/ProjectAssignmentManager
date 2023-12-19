package com.sirma.staffprojectmanager.controller.requst;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public class ProjectAssignmentRequest {

	@NotNull
	private Long employeeId;
	@NotNull
	private Long projectId;
	@NotNull
	@Pattern(regexp = "^([0-9]{4})\\/([0][1-9]|[1][0-2])\\/([0-2][0-9]|[3][0-1])|([0-2][0-9]|[3][0-1])\\/" +
	               "([0][1-9]|[1][0-2])\\/([0-9]{4})|([0][1-9]|[1][0-2])\\/([0-2][0-9]|[3][0-1])\\/(\\d{4})|([0-9]{4})-" +
	               "([0][1-9]|[1][0-2])-([0-2][0-9]|[3][0-1])|([0-2][0-9]|[3][0-1])-([0][1-9]|[1][0-2])-([0-9]{4})|" +
	               "([0][1-9]|[1][0-2])-([0-2][0-9]|[3][0-1])-(\\d{4})$", message = "Invalid date format!")
	private LocalDate dateFrom;
	private LocalDate dateTo;

	public ProjectAssignmentRequest() {
	}

	public ProjectAssignmentRequest(Long employeeId, Long projectId, LocalDate dateFrom, LocalDate dateTo) {
		this.employeeId = employeeId;
		this.projectId = projectId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
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
