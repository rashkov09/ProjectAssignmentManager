package com.sirma.staffprojectmanager.mapper;

import com.sirma.staffprojectmanager.model.ProjectAssignment;
import com.sirma.staffprojectmanager.model.dto.ProjectAssignmentDto;

public class PaMapper {


	public ProjectAssignment fromDTO(ProjectAssignmentDto data) {
		ProjectAssignment projectAssignment = new ProjectAssignment();
		projectAssignment.setId(data.getId());
		projectAssignment.setEmployeeId(data.getEmployeeId());
		projectAssignment.setProjectId(data.getProjectId());
		projectAssignment.setDateFrom(data.getDateFrom());
		projectAssignment.setDateTo(data.getDateTo());
		return projectAssignment;
	}


	public ProjectAssignmentDto toDTO(ProjectAssignment data) {
		ProjectAssignmentDto dto = new ProjectAssignmentDto();
		dto.setId(data.getId());
		dto.setEmployeeId(data.getEmployeeId());
		dto.setProjectId(data.getProjectId());
		dto.setDateFrom(data.getDateFrom());
		dto.setDateTo(data.getDateTo());
		return dto;
	}
}
