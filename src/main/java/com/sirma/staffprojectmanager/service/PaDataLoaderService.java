package com.sirma.staffprojectmanager.service;

import com.sirma.staffprojectmanager.accessor.FileAccessor;
import com.sirma.staffprojectmanager.exception.NoDataAvailableException;
import com.sirma.staffprojectmanager.mapper.Mapper;
import com.sirma.staffprojectmanager.mapper.PaMapper;
import com.sirma.staffprojectmanager.model.ProjectAssignment;
import com.sirma.staffprojectmanager.model.dto.OverlapProjectsDto;
import com.sirma.staffprojectmanager.model.dto.ProjectAssignmentDto;
import com.sirma.staffprojectmanager.repository.PaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaDataLoaderService implements ApplicationRunner {
	private final PaRepository paRepository;
	private final FileAccessor CSVAccessor;
	private final PaMapper paMapper;
	private final Mapper<ProjectAssignment> projectAssignmentMapper;

	@Autowired
	public PaDataLoaderService(
		PaRepository paRepository, FileAccessor csvAccessor,
		PaMapper paMapper, Mapper<ProjectAssignment>  projectAssignmentMapper) {
		this.paRepository = paRepository;
		CSVAccessor = csvAccessor;
		this.paMapper = paMapper;
		this.projectAssignmentMapper = projectAssignmentMapper;
	}

	private void loadData(){
		if (paRepository.findAll().isEmpty()) {
			CSVAccessor.read().stream().map(projectAssignmentMapper::mapFromString).forEach(paRepository::save);
		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		loadData();
	}

	public String getOverlappingProjects() {
		List<OverlapProjectsDto> overlapProjectsDtoList = paRepository.findOverlappingData();
		StringBuilder builder = new StringBuilder();
		OverlapProjectsDto
			overlapProjectsDto = overlapProjectsDtoList.stream().max(Comparator.comparing(OverlapProjectsDto::getOverlapDays)).orElseThrow();
		builder.append(overlapProjectsDto);
		builder.append(System.lineSeparator());
		overlapProjectsDtoList.stream().filter(result -> result.getEmp1().equals(overlapProjectsDto.getEmp1()) && result.getEmp2().equals(
			overlapProjectsDto.getEmp2())).forEach(project ->{
			builder.append("ProjectID: ").append(project.getProjectId()).append(", ").append("Total days: ").append(project.getTotalDays()).append("\n");
		});
		return builder.toString();
	}

	public List<ProjectAssignmentDto> getAllProjectAssignments() {
		List<ProjectAssignment> paList = paRepository.findAll();
		if (paList.isEmpty()){
			throw new NoDataAvailableException();
		}
		return paList.stream().map(paMapper::toDTO).collect(Collectors.toList());
	}
}
