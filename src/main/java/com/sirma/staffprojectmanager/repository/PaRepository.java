package com.sirma.staffprojectmanager.repository;

import com.sirma.staffprojectmanager.model.ProjectAssignment;
import com.sirma.staffprojectmanager.model.dto.ResultDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PaRepository extends JpaRepository<ProjectAssignment, Long> {

	@Query(nativeQuery = true, name = "findOverlappingData")
	List<ResultDto> findOverlappingData();
}
