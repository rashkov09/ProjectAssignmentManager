package com.sirma.staffprojectmanager.mapper;

import com.sirma.staffprojectmanager.model.ProjectAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProjectAssignmentMapper implements Mapper<ProjectAssignment> {
	private final DateMapper dateMapper;

	@Autowired
	public ProjectAssignmentMapper(DateMapper dateMapper) {
		this.dateMapper = dateMapper;
	}

	@Override
	public ProjectAssignment mapFromString(String input) {
		String[] data = input.split(",\\s+");
		Long employeeId= Long.parseLong(data[0]);
		Long projectId= Long.parseLong(data[1]);
		LocalDate dateFrom = dateMapper.mapFromString(data[2]);
		LocalDate dateTo = dateMapper.mapFromString(data[3]);
		return new ProjectAssignment(employeeId,projectId,dateFrom,dateTo);
	}

	@Override
	public String mapToString(ProjectAssignment input) {
		return null;
	}
}
