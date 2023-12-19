package com.sirma.staffprojectmanager.service;

import com.sirma.staffprojectmanager.accessor.FileAccessor;
import com.sirma.staffprojectmanager.mapper.Mapper;
import com.sirma.staffprojectmanager.model.ProjectAssignment;
import com.sirma.staffprojectmanager.model.dto.ResultDto;
import com.sirma.staffprojectmanager.repository.PaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PaDataLoaderService implements ApplicationRunner {
	private final PaRepository paRepository;
	private final FileAccessor CSVAccessor;
	private final Mapper<ProjectAssignment> projectAssignmentMapper;

	@Autowired
	public PaDataLoaderService(
		PaRepository paRepository, FileAccessor csvAccessor,
		Mapper<ProjectAssignment>  projectAssignmentMapper) {
		this.paRepository = paRepository;
		CSVAccessor = csvAccessor;
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
		List<ResultDto> resultDtoList = paRepository.findOverlappingData();
		StringBuilder builder = new StringBuilder();
		ResultDto resultDto= resultDtoList.stream().max(Comparator.comparing(ResultDto::getOverlapDays)).orElseThrow();
		builder.append(resultDto);
		builder.append(System.lineSeparator());
		resultDtoList.stream().filter(result -> result.getEmp1().equals(resultDto.getEmp1()) && result.getEmp2().equals(resultDto.getEmp2())).forEach(project ->{
			builder.append("ProjectID: ").append(project.getProjectId()).append(", ").append("Total days: ").append(project.getTotalDays()).append("\n");
		});
		return builder.toString();
	}
}
