package com.sirma.staffprojectmanager.mapper;

import com.sirma.staffprojectmanager.model.ProjectAssignment;

import java.time.LocalDate;

public class ProjectAssignmentMapper implements Mapper<ProjectAssignment> {

	private final Mapper<LocalDate> dateMapper;

	public ProjectAssignmentMapper(Mapper<LocalDate> dateMapper) {
		this.dateMapper = dateMapper;
	}

	@Override
	public ProjectAssignment mapFromString(String input) {
		String[] data = input.split(",\\s+");
		Long employeeId = Long.parseLong(data[0]);
		Long projectId = Long.parseLong(data[1]);
		LocalDate dateFrom = dateMapper.mapFromString(data[2]);
		LocalDate dateTo = dateMapper.mapFromString(data[3]);
		ProjectAssignment projectAssignment = new ProjectAssignment();
		projectAssignment.setEmployeeId(employeeId);
		projectAssignment.setProjectId(projectId);
		projectAssignment.setDateFrom(dateFrom);
		projectAssignment.setDateTo(dateTo);

		return projectAssignment;
	}

	@Override
	public String mapToString(ProjectAssignment input) {
		return null;
	}
}
