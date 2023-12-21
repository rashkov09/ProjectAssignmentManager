package com.sirma.staffprojectmanager.service;

import com.sirma.staffprojectmanager.accessor.FileAccessor;
import com.sirma.staffprojectmanager.controller.requst.ProjectAssignmentRequest;
import com.sirma.staffprojectmanager.controller.requst.ProjectAssignmentUpdateRequest;
import com.sirma.staffprojectmanager.exception.FileMissingException;
import com.sirma.staffprojectmanager.exception.InvalidDateFormatException;
import com.sirma.staffprojectmanager.exception.InvalidFileDataException;
import com.sirma.staffprojectmanager.exception.ProjectAssignmentNotFoundException;
import com.sirma.staffprojectmanager.mapper.Mapper;
import com.sirma.staffprojectmanager.mapper.PaMapper;
import com.sirma.staffprojectmanager.model.ProjectAssignment;
import com.sirma.staffprojectmanager.model.dto.OverlapProjectsDto;
import com.sirma.staffprojectmanager.model.dto.ProjectAssignmentDto;
import com.sirma.staffprojectmanager.repository.PaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class PaDataService implements ApplicationRunner, ApplicationListener<ContextClosedEvent> {

	private final static String DEFAULT_FILE_PATH = "src/main/resources/input/";
	private final static String INPUT_CSV_FILE_PATH = "src/main/resources/input/data.csv";
	private final static String HEADER = "EMPLOYEE_ID, PROJECT_ID, DATE_FROM, DATE_TO";
	private final static String NO_ENTRIES_FOUND_MESSAGE = "No entries found!";
	private final PaRepository paRepository;
	private final FileAccessor CSVAccessor;
	private final PaMapper paMapper;
	private final Mapper<ProjectAssignment> projectAssignmentMapper;

	@Autowired
	public PaDataService(
		PaRepository paRepository, FileAccessor csvAccessor,
		PaMapper paMapper, Mapper<ProjectAssignment> projectAssignmentMapper) {
		this.paRepository = paRepository;
		CSVAccessor = csvAccessor;
		this.paMapper = paMapper;
		this.projectAssignmentMapper = projectAssignmentMapper;
	}

	private void loadData(String filePath) {
		if (paRepository.findAll().isEmpty()) {
			try {
				CSVAccessor.read(filePath).stream().skip(1).map(projectAssignmentMapper::mapFromString)
				           .forEach(paRepository::save);
			} catch (NumberFormatException | InvalidDateFormatException e) {
				throw new InvalidFileDataException(e.getMessage());
			}
		}
	}

	@Override
	public void run(ApplicationArguments args) {
		try {
			loadData(INPUT_CSV_FILE_PATH);
		} catch (InvalidFileDataException e) {
			System.out.println("Data processing failed, please reprocess file!");
		}
	}

	public String getOverlappingProjects() {
		List<OverlapProjectsDto> overlapProjectsDtoList = paRepository.findOverlappingData();
		if (overlapProjectsDtoList.isEmpty()){
			return NO_ENTRIES_FOUND_MESSAGE;
		}
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

	public void deleteById(Long id) {
		paRepository.deleteById(id);
	}

	public void updateProjectAssignment(ProjectAssignmentUpdateRequest projectAssignmentUpdateRequest, Long id) {
		if (paRepository.updateProjectAssignmentById(projectAssignmentUpdateRequest, id) != 1) {
			throw new ProjectAssignmentNotFoundException(String.valueOf(id));
		}
	}

	public String reprocessFile(String fileName) {
		try {
			paRepository.clearData();
			loadData(DEFAULT_FILE_PATH + fileName);
		} catch (InvalidFileDataException e) {
			throw e;
		} catch (Exception e) {
			loadData(DEFAULT_FILE_PATH+"dataBackup.csv");
			throw new FileMissingException(DEFAULT_FILE_PATH + fileName+" -> Restored data from backup file!");
		}
		return "Data reloaded successfully!";
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		List<ProjectAssignment> projectAssignments = paRepository.findAll();
		List<String> updatedData =
			projectAssignments.stream().map(projectAssignmentMapper::mapToString).collect(Collectors.toList());
		updatedData.add(0, HEADER);
		try {
			CSVAccessor.write(updatedData);
		} catch (Exception e) {
			System.out.println("Backup failed!" + e.getMessage());
		}
	}
}
