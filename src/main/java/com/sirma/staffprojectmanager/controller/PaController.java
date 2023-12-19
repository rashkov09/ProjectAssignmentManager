package com.sirma.staffprojectmanager.controller;

import com.sirma.staffprojectmanager.model.dto.ProjectAssignmentDto;
import com.sirma.staffprojectmanager.service.PaDataLoaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/findMaxProjectOverlap")
	@Operation(
		summary = "Returns a pair of employees who have worked together on common projects for the longest period of " +
		          "time.")
	public ResponseEntity<String> getListOfOverlappingProjects() {
		return ResponseEntity.ok(paDataLoaderService.getOverlappingProjects());
	}
}
