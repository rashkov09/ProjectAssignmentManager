package com.sirma.staffprojectmanager.repository;

import com.sirma.staffprojectmanager.model.ProjectAssignment;
import com.sirma.staffprojectmanager.model.dto.OverlapProjectsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaRepository extends JpaRepository<ProjectAssignment, Long> {

	@Query(nativeQuery = true, name = "findOverlappingData")
	List<OverlapProjectsDto> findOverlappingData();

	@Query(value = "SELECT pa FROM ProjectAssignment pa WHERE " +
	               "(cast(:dateFrom as date) is null OR pa.dateFrom >= :dateFrom) AND " +
	               "(cast(:dateTo as date) is null OR COALESCE(pa.dateTo, CURRENT_DATE) <= :dateTo) AND " +
	               "(:employeeId IS NULL OR pa.employeeId = :employeeId) AND " +
	               "(:projectId IS NULL OR pa.projectId = :projectId)")
	List<ProjectAssignment> findProjectAssignmentsByFilter(
		@Param("employeeId") Long employeeId, @Param("projectId") Long projectId,
		@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
}
