package com.sirma.staffprojectmanager.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class ProjectAssignment extends BaseEntity{
	private Long employeeId;
	private Long projectId;
	private LocalDate dateFrom;
	private LocalDate dateTo;

	public ProjectAssignment(Long employeeId, Long projectId, LocalDate dateFrom, LocalDate dateTo) {
		this.employeeId = employeeId;
		this.projectId = projectId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public ProjectAssignment() {

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
