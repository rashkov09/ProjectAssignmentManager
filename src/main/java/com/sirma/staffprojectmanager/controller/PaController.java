package com.sirma.staffprojectmanager.controller;

import com.sirma.staffprojectmanager.model.dto.ResultDto;
import com.sirma.staffprojectmanager.service.PaDataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	@GetMapping("/overlapping")
	public ResponseEntity<List<ResultDto>> getListOfOverlappingProjects() {
		return ResponseEntity.ok(paDataLoaderService.getOverlappingProjects());
	}
}
