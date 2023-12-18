package com.sirma.staffprojectmanager.configuration;

import com.sirma.staffprojectmanager.mapper.DateMapper;
import com.sirma.staffprojectmanager.mapper.Mapper;
import com.sirma.staffprojectmanager.mapper.ProjectAssignmentMapper;
import com.sirma.staffprojectmanager.model.ProjectAssignment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class ApplicationBeanConfiguration {

	@Bean
	public Mapper<LocalDate> dateMapper(){
		return new DateMapper();
	}
		@Bean
	public Mapper<ProjectAssignment> projectAssignmentMapper(){
		return new ProjectAssignmentMapper(dateMapper());
	}
}
