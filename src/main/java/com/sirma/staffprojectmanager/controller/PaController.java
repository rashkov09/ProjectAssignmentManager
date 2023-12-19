package com.sirma.staffprojectmanager.controller;

import com.sirma.staffprojectmanager.controller.requst.ProjectAssignmentRequest;
import com.sirma.staffprojectmanager.model.ProjectAssignment;
import com.sirma.staffprojectmanager.model.dto.ProjectAssignmentDto;
import com.sirma.staffprojectmanager.service.PaDataLoaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "Project Assignment Controller", description = "Provides data for project assignments")
public class PaController {

	private final PaDataLoaderService paDataLoaderService;

	@Autowired
	public PaController(PaDataLoaderService paDataLoaderService) {
		this.paDataLoaderService = paDataLoaderService;
	}

	@GetMapping("/pa")
	@Operation(summary = "Returns project assignments from database base ot filter. Return all if no filter applied.")
	public ResponseEntity<List<ProjectAssignmentDto>> getAllAssignments(
		@RequestParam(value = "employeeId", required = false) Long employeeId,
		@RequestParam(value = "projectId", required = false) Long projectId,
		@RequestParam(value = "dateFrom", required = false) LocalDate dateFrom,
		@RequestParam(value = "dateTo", required = false) LocalDate dateTo
	) {
		return ResponseEntity.ok(
			paDataLoaderService.readFilteredProjectAssignments(employeeId, projectId, dateFrom, dateTo));
	}

	@PostMapping("/pa")
	@Operation(summary = "Create a project assignment.")
	public ResponseEntity<Void> createProjectAssignment(@RequestBody @Valid ProjectAssignmentRequest projectAssignmentRequest) {
		Long id = paDataLoaderService.createProjectAssigment(projectAssignmentRequest);

		URI location = UriComponentsBuilder.fromUriString("/pa/{id}")
		                                   .buildAndExpand(id)
		                                   .toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/findMaxProjectOverlap")
	@Operation(
		summary = "Returns a pair of employees who have worked together on common projects for the longest period of " +
		          "time.")
	public ResponseEntity<String> getListOfOverlappingProjects() {
		return ResponseEntity.ok(paDataLoaderService.getOverlappingProjects());
	}
}
