package com.sirma.staffprojectmanager.controller;

import com.sirma.staffprojectmanager.controller.requst.ProjectAssignmentRequest;
import com.sirma.staffprojectmanager.controller.requst.ProjectAssignmentUpdateRequest;
import com.sirma.staffprojectmanager.mapper.Mapper;
import com.sirma.staffprojectmanager.model.ProjectAssignment;
import com.sirma.staffprojectmanager.model.dto.ProjectAssignmentDto;
import com.sirma.staffprojectmanager.service.PaDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Project Assignment Controller", description = "Provides data for project assignments")
public class PaController {

	private final PaDataService paDataService;
	private final Mapper<LocalDate> dateMapper;

	@Autowired
	public PaController(PaDataService paDataService, Mapper<LocalDate> dateMapper) {
		this.paDataService = paDataService;
		this.dateMapper = dateMapper;
	}

	@GetMapping("/pa")
	@Operation(summary = "Returns project assignments from database based on filter. Return all if no filter applied.")
	public ResponseEntity<List<ProjectAssignmentDto>> getAllAssignments(
		@RequestParam(value = "employeeId", required = false) Long employeeId,
		@RequestParam(value = "projectId", required = false) Long projectId,
		@RequestParam(value = "dateFrom", required = false) String dateFrom,
		@RequestParam(value = "dateTo", required = false) String dateTo
	) {
		LocalDate formattedDateFrom  = dateMapper.mapFromString(dateFrom);
		LocalDate formattedDateTo = dateMapper.mapFromString(dateTo);
		return ResponseEntity.ok(
			paDataService.readFilteredProjectAssignments(employeeId, projectId, formattedDateFrom, formattedDateTo));
	}

	@PostMapping("/pa")
	@Operation(summary = "Create a project assignment.")
	public ResponseEntity<Void> createProjectAssignment(
		@RequestBody @Valid ProjectAssignmentRequest projectAssignmentRequest) {
		Long id = paDataService.createProjectAssigment(projectAssignmentRequest);

		URI location = UriComponentsBuilder.fromUriString("/pa/{id}")
		                                   .buildAndExpand(id)
		                                   .toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/pa/{id}")
	@Operation(summary = "Delete a record for project assignment by ID")
	public ResponseEntity<ProjectAssignment> deleteEmployeeById(@PathVariable Long id) {
		paDataService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("pa/{id}")
	@Operation(summary = "Update project assignment information.")
	public ResponseEntity<Void> updateProjectAssignment(
		@PathVariable Long id, @RequestBody @Valid ProjectAssignmentUpdateRequest projectAssignmentUpdateRequest) {
		paDataService.updateProjectAssignment(projectAssignmentUpdateRequest, id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/findMaxProjectOverlap")
	@Operation(
		summary = "Returns a pair of employees who have worked together on common projects for the longest period of " +
		          "time and the projects with the overlap time for each one. ")
	public ResponseEntity<String> getListOfOverlappingProjects() {
		return ResponseEntity.ok(paDataService.getOverlappingProjects());
	}
}
