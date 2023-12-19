package com.sirma.staffprojectmanager.controller;

import com.sirma.staffprojectmanager.model.dto.ResultDto;
import com.sirma.staffprojectmanager.service.PaDataLoaderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;

@RestController
public class PaController {

	private final PaDataLoaderService paDataLoaderService;

	@Autowired
	public PaController(PaDataLoaderService paDataLoaderService) {
		this.paDataLoaderService = paDataLoaderService;
	}

	@GetMapping("/")
	public ResponseEntity<String> sayHello() {
		paDataLoaderService.getOverlappingProjects();
		return ResponseEntity.ok("Hello, the controller is working!");
	}
	@GetMapping("/findMaxProjectOverlap")
	@Operation(summary = "Returns a pair of employees who have worked together on common projects for the longest period of time.")
	public ResponseEntity<String> getListOfOverlappingProjects() {
		return ResponseEntity.ok(paDataLoaderService.getOverlappingProjects());
	}
}
