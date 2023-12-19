package com.sirma.staffprojectmanager.service;

import ch.qos.logback.classic.net.SimpleSSLSocketServer;
import com.sirma.staffprojectmanager.accessor.FileAccessor;
import com.sirma.staffprojectmanager.controller.requst.ProjectAssignmentRequest;
import com.sirma.staffprojectmanager.mapper.Mapper;
import com.sirma.staffprojectmanager.mapper.PaMapper;
import com.sirma.staffprojectmanager.model.ProjectAssignment;
import com.sirma.staffprojectmanager.model.dto.OverlapProjectsDto;
import com.sirma.staffprojectmanager.model.dto.ProjectAssignmentDto;
import com.sirma.staffprojectmanager.repository.PaRepository;
import org.antlr.v4.runtime.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
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
		PaMapper paMapper, Mapper<ProjectAssignment> projectAssignmentMapper) {
		this.paRepository = paRepository;
		CSVAccessor = csvAccessor;
		this.paMapper = paMapper;
		this.projectAssignmentMapper = projectAssignmentMapper;
	}

	private void loadData() {
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
		TreeMap<OverlapProjectsDto, Integer> testAccum = new TreeMap<>();
		for (OverlapProjectsDto dto : overlapProjectsDtoList) {
			if (!testAccum.containsKey(dto)) {
				testAccum.put(dto, dto.getOverlapDays());
			} else {
				testAccum.put(dto, testAccum.get(dto) + dto.getOverlapDays());
			}
		}
		Map.Entry<OverlapProjectsDto, Integer> maxEntry = null;
		for (Map.Entry<OverlapProjectsDto, Integer> entry : testAccum.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		StringBuilder builder = new StringBuilder();
		builder
			.append(Objects.requireNonNull(maxEntry).getKey().getEmp1())
			.append(", ")
			.append(maxEntry.getKey().getEmp2())
			.append(", ")
			.append(maxEntry.getValue());
		builder.append(System.lineSeparator());
		for (OverlapProjectsDto dto : overlapProjectsDtoList) {
			if (dto.equals(maxEntry.getKey())) {
				builder.append(dto.getProjectId()).append(", ").append(dto.getOverlapDays()).append(System.lineSeparator());
			}
		}
		return builder.toString();
	}

	public List<ProjectAssignmentDto> readFilteredProjectAssignments(
		Long employeeId, Long projectId, LocalDate dateFrom, LocalDate dateTo) {
		List<ProjectAssignment> paList =
			paRepository.findProjectAssignmentsByFilter(employeeId, projectId, dateFrom, dateTo);
		if (paList.isEmpty()) {
			return new ArrayList<>();
		}
		return paList.stream().map(paMapper::toDTO).collect(Collectors.toList());
	}

	public Long createProjectAssigment(ProjectAssignmentRequest projectAssignmentRequest) {
		ProjectAssignment projectAssignment = new ProjectAssignment(projectAssignmentRequest.getEmployeeId(),
		                                                            projectAssignmentRequest.getProjectId(),
		                                                            projectAssignmentRequest.getDateFrom(),
		                                                            projectAssignmentRequest.getDateTo());
		return paRepository.save(projectAssignment).getId();
	}
}
