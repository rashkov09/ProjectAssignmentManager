package com.sirma.staffprojectmanager.model;

import com.sirma.staffprojectmanager.model.dto.ResultDto;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

import java.time.LocalDate;
@SqlResultSetMapping(
	name = "ResultDtoMapping",
	classes = {
		@ConstructorResult(
			targetClass = ResultDto.class,
			columns = {
				@ColumnResult(name = "emp1", type = Long.class),
				@ColumnResult(name = "emp2", type = Long.class),
				@ColumnResult(name = "project_id", type = Long.class),
				@ColumnResult(name = "overlap_days", type = Integer.class)
			}
		)
	}
)
@NamedNativeQuery(
	name = "findOverlappingData",
	query = "SELECT " +
	        "a.employee_id AS emp1, " +
	        "b.employee_id AS emp2, " +
	        "a.project_id AS project_id, " +
	        "(LEAST(COALESCE(a.date_to, CURRENT_DATE), COALESCE(b.date_to, CURRENT_DATE)) " +
	        " - GREATEST(a.date_from, b.date_from)) AS overlap_days " +
	        "FROM project_assignment a " +
	        "INNER JOIN project_assignment b ON a.employee_id < b.employee_id " +
	        "    AND a.project_id = b.project_id " +
	        "WHERE " +
	        "(a.date_from, COALESCE(a.date_to, CURRENT_DATE)) OVERLAPS (b.date_from, COALESCE(b.date_to, CURRENT_DATE)) " +
	        "AND NOT (a.employee_id = b.employee_id)",
	resultSetMapping = "ResultDtoMapping"
)
@Entity
public class ProjectAssignment extends BaseEntity {

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
